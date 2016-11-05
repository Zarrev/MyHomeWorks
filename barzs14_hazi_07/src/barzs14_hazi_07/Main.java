package barzs14_hazi_07;

import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

	private final static int W = 640;
	private final static int H = 480;

	private int size = 8;
	private int rnd = 0;
	
	private VBox vb = new VBox(5);
	private BorderPane bp = new BorderPane();
	private DigiTimer l = new DigiTimer();
	private GridPane gp = new GridPane();
	
	private Scene sc = new Scene(vb);
	private Stage theStage;
	
	private Game g;
	
	private void setLevel(String s) {
		if (s.equals("easy")) {
			rnd = 10;
		} else if (s.equals("normal")) {
			rnd = 30;
		} else if (s.equals("hard")) {
			rnd = 50;
		}
	}

	public void setOpenPage() {
		vb.setAlignment(Pos.CENTER);
		HBox hb = new HBox(2);
		HBox hb1 = new HBox(4);
		vb.setPrefSize(W/2, H/2);
		vb.setSpacing(10);
		hb.setAlignment(Pos.CENTER);
		hb1.setAlignment(Pos.CENTER);
		
		ToggleGroup group = new ToggleGroup();
		RadioButton rb1 = new RadioButton("easy");
		RadioButton rb2 = new RadioButton("normal");		
		RadioButton rb3 = new RadioButton("hard");
		
		rb1.setToggleGroup(group);
		rb1.setAlignment(Pos.CENTER_LEFT);
		rb2.setToggleGroup(group);
		rb2.setAlignment(Pos.CENTER_RIGHT);
		rb3.setToggleGroup(group);
		rb3.setAlignment(Pos.CENTER_LEFT);
		rb1.setSelected(true);

		Spinner<Integer> tsize = new Spinner<>(5, 15, 8);

		Button exit = new Button("Exit");
		Button start = new Button("Start");
		start.setOnAction((ActionEvent e) -> {
			this.size = tsize.getValueFactory().getValue();
			this.setLevel(((RadioButton) group.getSelectedToggle()).getText());
			setMainPage();
			l.counter(theStage);
			theStage.getScene().setRoot(bp); //TODO Itt szokott behalni, amikor megprobalok scene-t valtani, azaz a start gombra kattintva
			theStage.sizeToScene();
		});
		start.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				start.setEffect(new DropShadow());
			}
		});
		start.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				start.setEffect(null);
			}
		});
		exit.setOnAction((ActionEvent e) -> {
			Platform.exit();
		});
		Text t = new Text(0,10,"MINESWEEPER");
		vb.getChildren().add(t);
		t.setFont(Font.font("Amble CN", FontWeight.BOLD, 24));
		hb1.getChildren().add(new Text("Difficulty level:"));
		hb1.getChildren().add(rb1);
		hb1.getChildren().add(rb2);
		hb1.getChildren().add(rb3);
		vb.getChildren().add(hb1);
		hb.getChildren().add(new Text("Table size: "));
		hb.getChildren().add(tsize);
		vb.getChildren().add(hb);
		vb.getChildren().add(start);
		vb.getChildren().add(exit);
	}
	
	public void isWin(){
		if(g.isWin()){
			l.stopTime();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("YOU WIN!");
			alert.setContentText("Your time: "+ l.toString() +" \nWould you like to play again?");

			ButtonType buttonTypeOne = new ButtonType("Yes");
			ButtonType buttonTypeTwo = new ButtonType("Exit", ButtonData.CANCEL_CLOSE);
			alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonTypeOne){
				l.makeZero();
				theStage.getScene().setRoot(vb);
				theStage.sizeToScene();
			} 
			else {
			    Platform.exit();
			}
		}
	}
	
	public void zeroArea(Button tmp){
		for (Node node : gp.getChildren()) {
	        if(GridPane.getRowIndex(node) == GridPane.getRowIndex(tmp)+1 && GridPane.getColumnIndex(node) == GridPane.getColumnIndex(tmp)+1) { //++k
	        	setThis((Button)node);
	        }
	        if(GridPane.getRowIndex(node) == GridPane.getRowIndex(tmp)+1 && GridPane.getColumnIndex(node) == GridPane.getColumnIndex(tmp)) { //+0k
	        	setThis((Button)node);
	        }
	        if(GridPane.getRowIndex(node) == GridPane.getRowIndex(tmp)+1 && GridPane.getColumnIndex(node) == GridPane.getColumnIndex(tmp)-1) { //+-k
	        	setThis((Button)node);
	        }
	        
	        if(GridPane.getRowIndex(node) == GridPane.getRowIndex(tmp) && GridPane.getColumnIndex(node) == GridPane.getColumnIndex(tmp)+1) { //0+k
	        	setThis((Button)node);
	        }
	        if(GridPane.getRowIndex(node) == GridPane.getRowIndex(tmp) && GridPane.getColumnIndex(node) == GridPane.getColumnIndex(tmp)-1) { //0-k
	        	setThis((Button)node);
	        }
	        if(GridPane.getRowIndex(node) == GridPane.getRowIndex(tmp)-1 && GridPane.getColumnIndex(node) == GridPane.getColumnIndex(tmp)+1) { //-+k
	        	setThis((Button)node);
	        }
	        if(GridPane.getRowIndex(node) == GridPane.getRowIndex(tmp)-1 && GridPane.getColumnIndex(node) == GridPane.getColumnIndex(tmp)-1) { //--k
	        	setThis((Button)node);
	        }
	        if(GridPane.getRowIndex(node) == GridPane.getRowIndex(tmp)-1 && GridPane.getColumnIndex(node) == GridPane.getColumnIndex(tmp)) { //-0k
	        	setThis((Button)node);
	        }
	    }
	}
	
	public void setThis(Button tmp){
		int sss = g.getIt(GridPane.getRowIndex(tmp), GridPane.getColumnIndex(tmp));

		if(tmp.getText().equals("")){
			if(sss == -1){
				tmp.setStyle("-fx-background-color:   RED;");
				tmp.setText("B");
			}
			else{
				tmp.setStyle("-fx-background-color:   YELLOW;");
				String temp = "" + sss;
				tmp.setText(temp);
				g.setIt(GridPane.getRowIndex(tmp), GridPane.getColumnIndex(tmp));
				//System.out.println(g);
			}
			if(tmp.getText().equals("0")){
				this.zeroArea(tmp);
			}
			if (tmp.getText().equals("B")){
				l.stopTime();
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Confirmation Dialog");
				alert.setHeaderText("GAME OVER");
				alert.setContentText("Would you like to play again?");

				ButtonType buttonTypeOne = new ButtonType("Yes");
				ButtonType buttonTypeTwo = new ButtonType("Exit", ButtonData.CANCEL_CLOSE);
				alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == buttonTypeOne){
					l.makeZero();
					theStage.getScene().setRoot(vb);
					theStage.sizeToScene();
				} 
				else {
				    Platform.exit();
				}
			}
		}
	}
	
	public void setMainPage(){
		gp = new GridPane();
		g = new Game(size,rnd);
		//System.out.println(g);
		for(int i = 0; i < this.size; i++){
			ColumnConstraints cc = new ColumnConstraints();
			cc.setHalignment(HPos.CENTER);
			cc.setMinWidth(25);
			cc.setMaxWidth(100);
			gp.getColumnConstraints().add(cc);
		}
		for(int j = 0; j < this.size; j++){
			RowConstraints rc = new RowConstraints();
			rc.setValignment(VPos.CENTER);
			rc.setMinHeight(25);
			rc.setMaxHeight(100);
			gp.getRowConstraints().add(rc);
		}
		
		for(int i = 0; i < this.size; i++){
			for(int j = 0; j < this.size; j++){
				Button tmp = new Button("");
				tmp.setMaxSize(25, 25);
				tmp.addEventFilter(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>(){
		            @Override
		            public void handle(MouseEvent event) {
		                if (event.getButton() == MouseButton.SECONDARY) {
		                	if(tmp.getText().equals(""))
		                		tmp.setText("X");
		                	else if (tmp.getText().equals("X") || tmp.getText().equals("-1"))
		                		tmp.setText("");
		                    event.consume();
		                }
		            }
		        });
				tmp.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						setThis(tmp);
						isWin();
					}
				});
				gp.add(tmp,i,j);
			}
		}
		
		MenuBar mb = new MenuBar();
		Menu file = new Menu("File");
		MenuItem exit = new MenuItem("Exit");
		exit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Platform.exit();
			}
		});
		MenuItem nG = new MenuItem("New Game");
		nG.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				l.makeZero();
				theStage.getScene().setRoot(vb);
				theStage.sizeToScene();
			}
		});
		MenuItem info = new MenuItem("Info");
		info.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information about MineSweeper");
				alert.setHeaderText(null);
				alert.setContentText("Mouse Right Click is marking \n"
									+ "Mouse Left Click is uncover");
				alert.showAndWait();
			}
		});
		file.getItems().add(nG);
		file.getItems().add(info);
		file.getItems().add(exit);
		mb.getMenus().add(file);
		bp.setTop(mb);
		bp.setBottom(l.getL());
		bp.setCenter(gp);
		BorderPane.setAlignment(gp, Pos.CENTER);
		BorderPane.setAlignment(l.getL(), Pos.BOTTOM_CENTER);
		BorderPane.setAlignment(mb, Pos.TOP_CENTER);
		bp.setStyle("-fx-background-color:   grey;");
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		theStage = primaryStage;
	    this.setOpenPage();
	    theStage.setScene(sc);
	    theStage.setTitle("Minesweeper");
	    theStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
