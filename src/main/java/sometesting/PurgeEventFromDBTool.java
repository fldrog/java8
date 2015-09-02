package sometesting;

import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicLong;

import oracle.jdbc.driver.OracleConnection;

public class PurgeEventFromDBTool {

	public static void main(String[] argv) throws SQLException {

		System.out.println("-------- Oracle JDBC Connection Testing ------");

		final String DB_URL = "jdbc:oracle:thin:@172.30.152.141:1521:nb";
		final String DB_USERNAME = "nxtbdev_betradarfeed";
		final String DB_PASSWORD = "abp_betradarfeed";
		final String DB_SCHEMA = "NXTBDEV";
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (ClassNotFoundException e) {

			System.out.println("Where is your Oracle JDBC Driver?");
			e.printStackTrace();
			return;

		}

		System.out.println("Oracle JDBC Driver Registered!");
		OracleConnection connection = (OracleConnection) DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
		try {
			CallableStatement call = connection.prepareCall("call " + DB_SCHEMA + ".PREPARE_CONNECTION(1)");
			System.out.println("Prepare connection" + call.execute());

			// get all replay ids
			String getReplayFeedCodes = "select feedcode from " + DB_SCHEMA
					+ ".events where  REGEXP_LIKE(feedcode, 'BR:MATCH:([0-9]){10}([0-9]*)')";

			PreparedStatement stm = connection.prepareStatement(getReplayFeedCodes);
			stm.execute();

			final List<String> feedCodes = new ArrayList<>();

			while (stm.getResultSet().next()) {
				feedCodes.add(stm.getResultSet().getString("feedcode"));
			}
			stm.close();

			final AtomicLong total = new AtomicLong(feedCodes.size());
			ExecutorService exec = Executors.newFixedThreadPool(feedCodes.size());
			for (final String feedCode : feedCodes) {
				Runnable runnable = new Runnable() {

					@Override
					public void run() {
						OracleConnection connection = null;
						try {
							connection = (OracleConnection) DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
							CallableStatement call = connection.prepareCall("call " + DB_SCHEMA + ".PREPARE_CONNECTION(1)");
							System.out.println("Prepare connection" + call.execute());
							System.out.println("DELETING " + feedCode + " " + "/" + feedCodes.size());
							String FEED_CODE = feedCode;

							PreparedStatement stm = connection.prepareStatement("select id from " + DB_SCHEMA + ".events where feedcode ='"
									+ FEED_CODE + "'");
							stm.execute();
							stm.getResultSet().next();
							Long id = stm.getResultSet().getLong("id");
							System.out.println("ID=" + id);
							stm.close();

							call = connection.prepareCall("call " + DB_SCHEMA + ".DELETEEVENT(" + id + ")");
							call.execute();

							String d1 = "delete from " + DB_SCHEMA + ".feedqueuesummaries where feedcode ='" + FEED_CODE + "'";
							String d2 = "delete from " + DB_SCHEMA + ".feedqueueitemcontents where feedqueueitemid in (select id from "
									+ DB_SCHEMA + ".feedqueueitems where feedcode ='" + FEED_CODE + "')";
							String d3 = "delete from " + DB_SCHEMA + ".feedqueueitemmessages where feedqueueitemid in (select id from "
									+ DB_SCHEMA + ".feedqueueitems where feedcode ='" + FEED_CODE + "')";
							String d4 = "delete from " + DB_SCHEMA + ".feedqueueitemactions where feedqueueitemid in (select id from "
									+ DB_SCHEMA + ".feedqueueitems where feedcode ='" + FEED_CODE + "')";
							String d5 = "delete from " + DB_SCHEMA + ".feedqueueitems where feedcode ='" + FEED_CODE + "'";
							String d6 = "delete from " + DB_SCHEMA + ".deletedfeedcodes where feedcode ='" + FEED_CODE + "'";

							stm = connection.prepareStatement(d1);
							stm.execute();
							stm.close();

							stm = connection.prepareStatement(d2);
							stm.execute();
							stm.close();

							stm = connection.prepareStatement(d3);
							stm.execute();
							stm.close();

							stm = connection.prepareStatement(d4);
							stm.execute();
							stm.close();

							stm = connection.prepareStatement(d5);
							stm.execute();
							stm.close();

							stm = connection.prepareStatement(d6);
							stm.execute();
							stm.close();

							connection.commit();
							System.out.println("DELETED, remaining=" + total.decrementAndGet());
							if (total.get() == 0L) {
								System.exit(0);
							}
						} catch (Exception ex) {
							try {
								connection.rollback();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							ex.printStackTrace();
							System.out.println("Error while deleting this event...skipping it.Remaining: " + total.decrementAndGet());
							if (total.get() == 0L) {
								System.exit(0);
							}
						} finally {
							try {
								connection.close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				};
				exec.execute(runnable);
			}

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			connection.rollback();
			return;

		} finally {
			connection.close();
		}

	}
}