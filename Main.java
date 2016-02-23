package cc;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		long startTime = System.currentTimeMillis();
		ArrayList<Thread> threadPool = new ArrayList<Thread>();
		SensorAnalyzer.setOutput("src/output.txt");
		/*for (int i = 0 ; i< 100; i++) {
			new SensorAnalyzer("input/" + 1).run();
		}*/
		for (int i = 0; i < 100; i++) {
			Thread t = new Thread(new SensorAnalyzer("input/" + i));
			threadPool.add(t);
			t.start();

		}
		for (Thread t : threadPool) {
			t.join();
		}
		System.out.println(String.valueOf(SensorAnalyzer.truncatedFormat.format(SensorAnalyzer.getAverage())));
		SensorAnalyzer.finish();
		long stopTime = System.currentTimeMillis();
	      long elapsedTime = stopTime - startTime;
	      System.out.println(elapsedTime);
	}

}
