package cc;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GenerateInput {
	public static void main(String[] args) throws IOException {
		for (int i = 0; i < 100; i++) {
			BufferedWriter bw = new BufferedWriter(new FileWriter("input/" + i));
			bw.write(Math.random() * 10 + "\n");
			int numCount = (int) (Math.random() * 200000);
			String delim = "";
			for (int j = 0; j < numCount; j++) {
				bw.write(delim + Math.random() * 10);
				delim = ",";
			}
			bw.close();
		}
	}
}
