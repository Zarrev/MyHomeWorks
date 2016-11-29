package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;


public class Main {

	public static final int port = 19991;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String words[] = {"valami","őrületes","hogy","mi","történik","anya","ezt","nézd","meg", "hogy", "csal"};
		
		try {
			ServerSocket socket = new ServerSocket(port);
			while (true) {
				Socket client = socket.accept();
				new Thread(new Client(client, words[new Random().nextInt(words.length)])).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
