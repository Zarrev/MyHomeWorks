package Client;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.Socket;
import java.net.UnknownHostException;

import Server.Main;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class CMain extends Application {

	private static final int lifes = 10;
	private static final double progressDownNum = (double)lifes/100;
	private boolean inProgress;
	private Button bt;
	private VBox vb;
	private ProgressBar pb;
	private TextField tf;
	private Label l;
	private Socket socket;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		vb = new VBox();
		pb = new ProgressBar(1);
		l = new Label("SomeThingWrong!");
		tf = new TextField("");
		bt = new Button("Send!");
		bt.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if (!inProgress) {
					inProgress = true;
					new Thread(new Explorer(tf.getText(),socket, CMain.this)).start();
				}
			}
		});
		vb.getChildren().addAll(pb,l,tf,bt);
		inProgress = true;
		try {
			socket = new Socket("localhost",Main.port);
			new Thread(new Recriver(socket, this)).start();
		} catch (UnknownHostException e2) {
			e2.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		Scene sc = new Scene(vb);
		primaryStage.setScene(sc);
		primaryStage.show();
	}

	public void set(String word) {
		// TODO Auto-generated method stub
		inProgress = false;
		if (word == null) {
			System.exit(1);
		}
		l.setText(word);
	}

	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	public void update(String word) {
		// TODO Auto-generated method stub
		inProgress = false;
		if (word == null) {
			return;
		}
		if (word.equals("")) {
			pb.setProgress(round(pb.getProgress() - progressDownNum,1));
			if (pb.getProgress() == 0) {
				l.setText("Game over!");
				bt.setDisable(true);
				tf.setDisable(true);
			}
		} else {
			if (!word.contains("_")) {
				l.setText("You win, the word is: " + word);
				bt.setDisable(true);
				tf.setDisable(true);
			} else {
				l.setText(word);
			}
		}
	}

}
