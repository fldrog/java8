package java8.lambda;

public class RunnableLambda {

	public static void main(String[] args) throws InterruptedException {
		Runnable r = () -> {
			System.out.println("Runnable lambda");
		};

		Thread t = new Thread(r);
		t.start();
		t.join();
	}
}
