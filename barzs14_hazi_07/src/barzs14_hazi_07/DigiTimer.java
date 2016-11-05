package barzs14_hazi_07;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public final class DigiTimer{

	private int min = 0;
	private String minS;
	private int sec = 0;
	private String secS;
	Timer timer;
	private Label l = new Label("00:00");
	
	public DigiTimer() {
		l.setFont(Font.loadFont("file:fonts/digital-7.ttf", 40));
		secS = "00";
		minS = "00";
		l.setTextFill(Color.WHITE);
		
	}
	
	public void stopTime(){
		timer.cancel();
        timer.purge();
	}
	
	public void makeZero(){
		this.min = 0;
		this.sec = 0;
		stopTime();
	}
	
	private void setMin() {
		this.min++;
		if (this.min < 10)
			minS = "0" + this.min;
		else
			minS = "" + this.min;
	}
	
	private void setSec() {
		if(this.sec < 59)
			this.sec++;
		else{
			this.sec = 0;
			this.setMin();
		}
		if (this.sec < 10)
			secS = "0" + this.sec;
		else
			secS = "" + this.sec;
	}
	
	public Label getL() {
		return l;
	}

	@Override
	public String toString() {
		return minS +":"+ secS;
	}
	
	public void counter(Stage s){
		timer =  new Timer();
		timer.schedule(new TimerTask() {
		    public void run() {
		         Platform.runLater(new Runnable() {
		            public void run() {
		            	setSec();
						l.setText(minS+":"+secS);
		            }
		        });
		        if(!s.isShowing()){
		        	stopTime();
		        	return;
		        }
		    }
		}, 1000, 1000);
	}
}

