package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javafx.application.Platform;



public class Explorer implements Runnable {
	private String reply, explore;
	private Socket socket;
	private CMain cMain;
	
	public Explorer(String explore, Socket socket, CMain cMain) {
		super();
		this.explore = explore;
		this.socket = socket;
		this.cMain = cMain;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			new PrintWriter(socket.getOutputStream(), true).println(explore);
			reply = new BufferedReader(new InputStreamReader(
					socket.getInputStream())).readLine();
			if (reply.equals("Wrong")) {
				reply = "";
			}
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					cMain.update(reply);
				}
			});

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
