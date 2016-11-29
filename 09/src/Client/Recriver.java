package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javafx.application.Platform;


public class Recriver implements Runnable {
	public Socket socket;
	private CMain cMain;
	private String word;

	public Recriver(Socket socket, CMain cMain) {
		super();
		this.socket = socket;
		this.cMain = cMain;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			word = new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine();

			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					cMain.set(word);
				}
			});

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
