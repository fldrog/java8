package sometesting;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class ReplayeEventsReplayServer {
	public static void main(String[] args) throws IOException {
		final String base = "http://gdc-dev-feedtx.msgreen.dom:8282/replay/";
		String eventIds = "BR:MATCH:7588836,BR:MATCH:7678938,BR:MATCH:7678942,BR:MATCH:6820788,BR:MATCH:7678940,BR:MATCH:7678892,BR:MATCH:7678936,BR:MATCH:6820790,BR:MATCH:7678860,BR:MATCH:7678872,BR:MATCH:7679088,BR:MATCH:7678870,BR:MATCH:7678368,BR:MATCH:7679018,BR:MATCH:7674862,BR:MATCH:7674848,BR:MATCH:7679060,BR:MATCH:7678880,BR:MATCH:7678834,BR:MATCH:7678372,BR:MATCH:7675094,BR:MATCH:7657830,BR:MATCH:7678044,BR:MATCH:7678440,BR:MATCH:7678786,BR:MATCH:7679160,BR:MATCH:7678812,BR:MATCH:7675172,BR:MATCH:7678818,BR:MATCH:7678816,BR:MATCH:7589120,BR:MATCH:7641144,BR:MATCH:7679056,BR:MATCH:7675092,BR:MATCH:7678144,BR:MATCH:7678034,BR:MATCH:6575327,BR:MATCH:7678134,BR:MATCH:7678012,BR:MATCH:7678376,BR:MATCH:7674846,BR:MATCH:7674850,BR:MATCH:7678366,BR:MATCH:7674856,BR:MATCH:7674858,BR:MATCH:7678448,BR:MATCH:7678868,BR:MATCH:7675164,BR:MATCH:7677976,BR:MATCH:7677986,BR:MATCH:7677978,BR:MATCH:7679158,BR:MATCH:7661684,BR:MATCH:7675232,BR:MATCH:7678874,BR:MATCH:7678362,BR:MATCH:7679166,BR:MATCH:7679054,BR:MATCH:7679162,BR:MATCH:7661864,BR:MATCH:7675170,BR:MATCH:7678862,BR:MATCH:7675228,BR:MATCH:7678854,BR:MATCH:7675166,BR:MATCH:7684032,BR:MATCH:7678866,BR:MATCH:7678452,BR:MATCH:7678782,BR:MATCH:7678780,BR:MATCH:7678784,BR:MATCH:7678446,BR:MATCH:7551848,BR:MATCH:7675224,BR:MATCH:7678036,BR:MATCH:7675230,BR:MATCH:6820786,BR:MATCH:6820784,BR:MATCH:7674994,BR:MATCH:7678032,BR:MATCH:6996682,BR:MATCH:7589132,BR:MATCH:7673836,BR:MATCH:7678364,BR:MATCH:7678864,BR:MATCH:7679052,BR:MATCH:7678360,BR:MATCH:7675090,BR:MATCH:7678858,BR:MATCH:7678852,BR:MATCH:7675236,BR:MATCH:7678370,BR:MATCH:7678374,BR:MATCH:7673978,BR:MATCH:7673832,BR:MATCH:7661962,BR:MATCH:7662102,BR:MATCH:7676232,BR:MATCH:7661728,BR:MATCH:7675104";
		// String eventIds = "BR:MATCH:7588114";
		System.out.println(eventIds.split(",").length);
		ExecutorService thPool = Executors.newFixedThreadPool(eventIds.split(",").length);
		int i = 0;
		for (final String event : eventIds.split(",")) {
//			CloseableHttpClient httpclient = HttpClients.createDefault();
//			Integer delay = new Random().nextInt(500) + 1000;
//			HttpGet get = new HttpGet(base + event.split(":")[2] + "?delayMs=" + delay);
//			System.out.println(Thread.currentThread() + "Replaying: " + get);
//			httpclient.execute(get).close();

			// URLConnection conn = url.openConnection();
			// conn.connect();
			// BufferedReader reader = new BufferedReader(new
			// InputStreamReader(conn.getInputStream()));
			// String line = null;
			// while ((line = reader.readLine()) != null) {
			// System.out.println(line);
			// }
//			System.out.println(Thread.currentThread() + "Replayed: " + get);
//			System.out.println(i++);
			thPool.execute(new Runnable() {

				@Override
				public void run() {
					try {
						CloseableHttpClient httpclient = HttpClients.createDefault();
						Integer delay = new Random().nextInt(500) + 1500;
						HttpGet get = new HttpGet(base + event.split(":")[2] + "?delayMs=" + delay);
						System.out.println(Thread.currentThread() + "Replaying: " + get);
						httpclient.execute(get).close();

						// URLConnection conn = url.openConnection();
						// conn.connect();
						// BufferedReader reader = new BufferedReader(new
						// InputStreamReader(conn.getInputStream()));
						// String line = null;
						// while ((line = reader.readLine()) != null) {
						// System.out.println(line);
						// }
						System.out.println(Thread.currentThread() + "Replayed: " + get);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			});

		}

	}
}
