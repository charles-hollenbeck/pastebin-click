package com.pastebinclick;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.awt.Desktop;
import javax.swing.WindowConstants;

public class PasteBin {
	public static void main(String[] args) throws IOException,
			URISyntaxException {
		Config uData = new Config("/var/lib/pastebin-click/USER.config");
		ArrayList<String> loginData = uData.getConfig();
		String user = "", passwd = "", token = "";
		if (loginData.size() == 2) {
			user = loginData.get(0);
			passwd = loginData.get(1);
		} else
			token = loginData.get(0);
		ArrayList<String> files = new ArrayList<String>();
		int n = 0;
		for (int i = 0; i < args.length; i++) {
			if (args[i].substring(0, 1).equals("/")) {
				files.add(args[i]);
				n++;
			} else
				files.set(n - 1, files.get(n - 1) + " " + args[i]);
		}
		for (int i = 0; i < files.size(); i++) {
			FileReader fr = new FileReader(files.get(i));
			String code = fr.getText();
			fr.parseFileName();
			String filename = fr.getFileName();
			String format = fr.getFormat();

			String output = "";
			if (format != "false") {
				API paste = new API("ec59b96864a900766beb39972ae08580");
				if (!(user.equals("")) && !(passwd.equals(""))) {
					String log = paste.login(user, passwd);
					if (!log.equals("false"))
						uData.updateConfig(log);
				} else {
					paste.setToken(token);
				}
				output = paste.makePaste(code, filename, format);
			}
			if (output.equals("")) {
				Dialog error = new Dialog("No format for " + fr.getExt());
				error.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				error.setVisible(true);
			} else if (!output.substring(0, 4).equals("http")) {
				Dialog error = new Dialog(output);
				error.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				error.setVisible(true);
			} else {
				String[] sUrl = output.split("[/]");
				String key = sUrl[sUrl.length - 1].replace("%0D", "");
				URI url = new URI("http", "pastebin.com", "/" + key, null);
				Desktop.getDesktop().browse(url);
			}
		}
	}

}