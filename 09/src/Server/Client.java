package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable{
	private Socket socket;
	private String word, hiddenWord;
	
	public Client(Socket socket, String word) {
		super();
		this.socket = socket;
		this.word = word;
//		this.hiddenWord = word.replaceAll("_", "_");
		StringBuilder sb = new StringBuilder("");
		for(int i = 0; i < word.length(); i++){
			sb.append("_");
		}
		this.hiddenWord = sb.toString();
	}
	private String check(String str) {
		if (str.length() != 1) {
			return "Wrong";
		}
		if (word.contains(str)) {
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < word.length(); i++) {
				if (word.charAt(i) == str.charAt(0) || hiddenWord.charAt(i) != '_') {
					builder.append(word.charAt(i));
				} else {
					builder.append('_');
				}
			}
			hiddenWord = builder.toString();
			return hiddenWord;
		} else {
			return "Wrong";
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
			pw.println(hiddenWord);
			String line = null;
			while ((line = br.readLine()) != null) {
				pw.println(check(line));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	

}
