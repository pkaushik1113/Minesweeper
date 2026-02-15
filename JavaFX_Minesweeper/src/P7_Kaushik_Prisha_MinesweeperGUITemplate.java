import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;



/*
Name:       Prisha Kaushik
Date:       03/10/2025
Period:     7

Is this lab fully working?  Yes

Create a HW Quiz question that any student who completed this lab would reasonably be expected
to complete within 5 minutes.
How do you use a drag feature to change the tile type?
*/


public class P7_Kaushik_Prisha_MinesweeperGUITemplate extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	

	public void start(Stage stage) {
		
// ROOT
		BorderPane root = new BorderPane();
		
		
// IMAGES
		Image blankTile = new Image("file:minesweeper_images/blank.gif");
		Image smileFace = new Image("file:minesweeper_images/face_smile.gif");
		
		
// TOP GAME MENU
		Menu game = new Menu("Game Menu");
		MenuItem beg = new MenuItem("New Beginner Game");
		MenuItem inter = new MenuItem("New Intermediate Game");
		MenuItem adv = new MenuItem("New Advanced Game");
		MenuItem custom = new MenuItem("New Custom Game");
		game.getItems().addAll(beg, inter, adv, custom);
		
		Menu options = new Menu("Options Menu");
		MenuItem numMines = new MenuItem("Set Number of Mines");
		MenuItem gridSize = new MenuItem("Set Grid Size");
		options.getItems().addAll(numMines, gridSize);
		
		Menu help = new Menu("Help Menu");
		MenuItem howPlay = new MenuItem("How to Play");
		MenuItem abt = new MenuItem("About");
		help.getItems().addAll(howPlay, abt);
		
		MenuBar bar = new MenuBar();
		bar.getMenus().addAll(game, options, help);
		HBox top = new HBox();
		top.setAlignment(Pos.CENTER_LEFT);
		top.getChildren().addAll(bar);
		root.setTop(top);
		
// CENTER BOARD
		
		
		GridPane board = new GridPane();
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				ImageView obj = new ImageView(blankTile);
				board.add(obj, j, i);
			}
		}
		board.setAlignment(Pos.CENTER);
		root.setCenter(board);
		
// BOTTOM INFO
		
		VBox mines = new VBox(5);
		Label mineTitle = new Label("Mines Remaining");
		Label mineNum = new Label("10");
		mines.setAlignment(Pos.CENTER);
		mines.getChildren().addAll(mineTitle, mineNum);
		
		ImageView smile = new ImageView(smileFace);
		
		VBox time = new VBox(5);
		Label timeTitle = new Label("Time Elapsed");
		Label timeNum = new Label("0");
		time.setAlignment(Pos.CENTER);
		time.getChildren().addAll(timeTitle, timeNum);
		HBox info = new HBox(15);
		info.setAlignment(Pos.CENTER);
		info.getChildren().addAll(mines, smile, time);
		root.setBottom(info);
		
		
		
// SCENE AND SHOW STAGE
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setHeight(400);
		stage.setWidth(300);
		stage.show();
		
	}
	
	
}