package object;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import ui.KingLogin;

public class ReadFile {
	public static ArrayList<Account> accounts = new ArrayList<Account>();

	public static void init() {
		try {
			FileReader fr = new FileReader("account.txt");
			BufferedReader br = new BufferedReader(fr);
			String s;

			while ((s = br.readLine()) != null) {
				String s1[] = s.split("\t");
				Account obj = new Account(s1[0], s1[1], s1[2]);
				accounts.add(obj);
				// for (String string : s1) {
				//
				// System.out.println(string);
				// }

			}
			fr.close();

		} catch (IOException e) {
			e.printStackTrace();
			KingLogin.isFirstRun=true;
			Account account = new Account("test", "test", "0");
			PrintWriter out = WriteFile.openWriter("account.txt");
			WriteFile.writeMovie(account, out);
			out.close();
			init();
		}

	}

}