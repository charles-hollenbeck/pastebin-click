package com.pastebinclick;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Config extends FileReader {
	public Config(String file) {
		super(file);
	}

	public ArrayList<String> getConfig() {
		String data = this.getText();
		String[] tokens = data.split("[=\n]");
		ArrayList<String> out = new ArrayList<String>();
		for (int i = 1; i < tokens.length; i += 2)
			out.add(tokens[i]);
		return out;
	}

	public void updateConfig(String token) {
		FileWriter fstream;
		try {
			fstream = new FileWriter(this.getFile());
			BufferedWriter out = new BufferedWriter(fstream);
			out.write("TOKEN=" + token);
			// Close the output stream
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
