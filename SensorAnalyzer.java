package cc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class SensorAnalyzer implements Runnable {

	private volatile static double allTotal = 0;
	private volatile static int allCount = 0;
	private static BufferedWriter bw;
	private static String delim = "";
	public final static DecimalFormat truncatedFormat = new DecimalFormat("#.##");

	private BufferedReader br;

	private String filename;
	
	public static void setOutput(String outputfile) throws IOException {
		bw = new BufferedWriter(new FileWriter(outputfile));
	}
	
	public static void finish() throws IOException {
		bw.close();
	}

	public SensorAnalyzer(String filename) throws FileNotFoundException {
		this.filename = filename;
		this.br =  new BufferedReader(new FileReader(filename));
	}

	@Override
	public void run() {
		String line;
		double scaling;
		try {
			line = br.readLine();
			scaling = Double.parseDouble(line);
		} catch (IOException e) {
			throw new Error("Scaling factor not found");
		}

		String [] split;
		double total = 0;
		int count = 0;
		try {
			if ((line = br.readLine()) != null) {
				split = line.split(",");
				for (String intstr : split ) {
					double measure = Double.parseDouble(intstr) * scaling;
					total += measure;
					count ++;
					allTotal += measure;
					allCount++;
					

				}
				synchronized(bw) {
					bw.write(delim + filename + "::" + truncatedFormat.format(total / count));
					delim = ",";
					bw.flush();
				}
			}
		} catch (IOException e) {

		}
	}

	public static double getAverage() {
		return allTotal / allCount;
	}

}
