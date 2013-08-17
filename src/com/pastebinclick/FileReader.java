package com.pastebinclick;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
	private String file;
	private String ext;
	private String name;

	public FileReader(String file) {
		this.file = file;
	}

	public String getExt() {
		return this.ext;
	}

	public String getFile() {
		return this.file;
	}

	public String getFileExt() {
		return this.ext;
	}

	public String getFileName() {
		return this.name;
	}

	public String getFormat() {
		String[] exts = { "txt", "asp", "sh", "c", "css", "html", "java", "js",
				"jq", "lsl", "php", "py", "json", "xml" };
		String[] formats = { "None", "ASP", "Bash", "C", "CSS", "HTML5",
				"Java", "JavaScript", "jQuery", "LindenScripting", "PHP",
				"Python", "JavaScript", "XML" };
		for (int i = 0; i < exts.length; i++)
			if (exts[i].equals(this.ext))
				return formats[i];
		return "false";
	}

	public String getText() {
		String text = "";
		File file = new File(this.file);
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				text += line + "\n";
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return text;
	}

	public void parseFileName() {
		String[] tokens = this.file.split("[/]");
		int loc = tokens.length - 1;
		String fileName = tokens[loc];
		String[] rmExt = fileName.split("[.]");
		fileName = rmExt[0];
		String fileExt = rmExt[rmExt.length - 1];
		this.ext = fileExt;
		this.name = fileName;
	}
}
