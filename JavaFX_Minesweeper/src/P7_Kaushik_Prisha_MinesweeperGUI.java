import java.io.File;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.scene.Node;
import javafx.scene.Scene;




public class P7_Kaushik_Prisha_MinesweeperGUI extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
// CENTER
	GridPane board;
	EffectHandler fx = new EffectHandler();
	
// IMAGES
		Image blankTile = new Image("file:minesweeper_images/blank.gif");
		Image flagTile = new Image("file:minesweeper_images/bomb_flagged.gif");
		Image smileFace = new Image("file:minesweeper_images/face_smile.gif");
		
		Image deadFace = new Image("file:minesweeper_images/face_dead.gif");
		Image oohFace = new Image("file:minesweeper_images/face_ooh.gif");
		Image winFace = new Image("file:minesweeper_images/face_win.gif");
		
		Image questionTile = new Image("file:minesweeper_images/bomb_question.gif");
		Image num0 = new Image("file:minesweeper_images/num_0.gif");
		Image num1 = new Image("file:minesweeper_images/num_1.gif");
		Image num2 = new Image("file:minesweeper_images/num_2.gif");
		Image num3 = new Image("file:minesweeper_images/num_3.gif");
		Image num4 = new Image("file:minesweeper_images/num_4.gif");
		Image num5 = new Image("file:minesweeper_images/num_5.gif");
		Image num6 = new Image("file:minesweeper_images/num_6.gif");
		Image num7 = new Image("file:minesweeper_images/num_7.gif");
		Image num8 = new Image("file:minesweeper_images/num_8.gif");
		
		Image bombDead = new Image("file:minesweeper_images/bomb_death.gif");
		Image bombWrong = new Image("file:minesweeper_images/bomb_wrong.gif");
		Image bomb = new Image("file:minesweeper_images/bomb_revealed.gif");
		
		Image title = new Image("file:minesweeper_images/title.jpg");
		
	
// MODEL
		P7_Kaushik_Prisha_MinesweeperModel model;
		int rows = 8;
		int cols = 8;
		boolean run = true;
		boolean first = true;
		
		
// BOTTOM INFO
		ImageView smile;
		Label mineNum;
		Label timeNum;
		long seconds = 0;
		AnimationTimer timer;
		
		
// TOP MENU
		MenuItem beg;
		MenuItem abt;
		MenuItem inter;
		MenuItem adv;
		MenuItem custom;
		MenuItem exit;
		MenuItem numMines;
		MenuItem gridSize;
		MenuItem howPlay;
		
		Stage about;
		Button abtOk;
		
		Stage homeScreen;
		Button revert;
		Button newr;
		VBox square;
		Label exited;
		
		WebView instructions;
		Button okay;
		
		
// POWER
		Stage power;
		Handler handler;
		BorderPane root;
		Scene scene;
		Scene howTo;
		
		
	public void start(Stage stage) {
		power = stage;
		
// HANDLER
		handler = new Handler();
		
// ROOT
		root = new BorderPane();
		
// MODEL
		model = new P7_Kaushik_Prisha_MinesweeperModel(rows, cols, 10);
		
// TOP GAME MENU
		MenuHandler han = new MenuHandler();
		Menu game = new Menu("Game Menu");
		beg = new MenuItem("New Beginner Game");
		beg.setOnAction(han);
		inter = new MenuItem("New Intermediate Game");
		inter.setOnAction(han);
		adv = new MenuItem("New Advanced Game");
		adv.setOnAction(han);
		custom = new MenuItem("New Custom Game");
		custom.setOnAction(han);
		exit = new MenuItem("Exit");
		exit.setOnAction(han);
		game.getItems().addAll(beg, inter, adv, custom, exit);
		
		Menu options = new Menu("Options Menu");
		numMines = new MenuItem("Set Number of Mines");
		numMines.setOnAction(han);
		gridSize = new MenuItem("Set Grid Size");
		gridSize.setOnAction(han);
		options.getItems().addAll(numMines, gridSize);
		
		Menu help = new Menu("Help Menu");
		howPlay = new MenuItem("How to Play");
		howPlay.setOnAction(han);
		abt = new MenuItem("About");
		abt.setOnAction(han);
		help.getItems().addAll(howPlay, abt);
		
		MenuBar bar = new MenuBar();
		bar.getMenus().addAll(game, options, help);
		HBox top = new HBox();
		top.setAlignment(Pos.CENTER_LEFT);
		top.getChildren().addAll(bar);
		root.setTop(top);
		
// CENTER BOARD
		board = newBoard(rows, cols);
		board.setAlignment(Pos.CENTER);
		root.setCenter(board);
		
// BOTTOM INFO
		
		VBox mines = new VBox(5);
		Label mineTitle = new Label("Mines Remaining");
		mineNum = new Label("" + model.getNumFlagsLeft());
		mines.setAlignment(Pos.CENTER);
		mines.getChildren().addAll(mineTitle, mineNum);
		
		smile = new ImageView(smileFace);
		smile.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(run) {
					smile.setImage(oohFace);
				}
			}
		});
		
		smile.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(run) {
					smile.setImage(smileFace);
				}
			}
		});
		
		
		
		VBox time = new VBox(5);
		Label timeTitle = new Label("Time Elapsed");
		timeNum = new Label("0");
		time.setAlignment(Pos.CENTER);
		time.getChildren().addAll(timeTitle, timeNum);
		HBox info = new HBox(15);
		info.setAlignment(Pos.CENTER);
		info.getChildren().addAll(mines, smile, time);
		root.setBottom(info);
		
// TIMER
		
		timer = new AnimationTimer() {
			private long then = Long.MIN_VALUE;

			@Override
			public void handle(long now) {
				if(run) {
					if(then == Long.MIN_VALUE) {
						then = now;
					} else if(now - then >= 1e9) {
						seconds++;
						timeNum.setText("" + seconds);
						//IN THE FUTURE, THEN WILL BE NOW
						then = now;
					}
				}
				
			}
		};
		
		

// POPUPS
		
		EventHandler<ActionEvent> button = new EventHandler<ActionEvent> () {
			@Override
			public void handle(ActionEvent event) {
				if((Button)(event.getSource()) == abtOk) {
					about.close();
				} else if((Button)(event.getSource()) == revert) {
					run = true;
					homeScreen.close();
					power.show();
				} else if((Button)(event.getSource()) == newr) {
					homeScreen.close();
					power.show();
					model = new P7_Kaushik_Prisha_MinesweeperModel(8, 8, 10);
					rows = 8;
					cols = 8;
					run = true;
					first = true;
					updateView();
					seconds = 0;
					timeNum.setText("" + seconds);
					mineNum.setText("" + model.getNumFlagsLeft());
					//timer.start();
					smile.setImage(smileFace);
				}
			}
		};
		
		about = new Stage();
		VBox box = new VBox(10);
		box.setAlignment(Pos.CENTER);
		Label abtTitle = new Label("Minesweeper");

		Label abtVersion = new Label("Version 2.0");
		Label abtAuthor = new Label("By Prisha Kaushik");
		abtOk = new Button("OK");
		abtOk.setOnAction(button);
		box.getChildren().addAll(abtTitle, abtVersion, abtAuthor, abtOk);
		Scene abtScene = new Scene(box);
		about.setScene(abtScene);
		//about.show();
		
		
		homeScreen = new Stage();
		square = new VBox(10);
		square.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		ImageView thing = new ImageView(title);
		exited = new Label("Exited Game");
		exited.setTextFill(Color.RED);
		revert = new Button("Return to Game");
		revert.setOnAction(button);
		newr = new Button("Start New Game");
		newr.setOnAction(button);
		square.getChildren().addAll(thing, newr);
		square.setAlignment(Pos.CENTER);
		Scene exitScene = new Scene(square);
		exitScene.setFill(Color.BLACK);
		homeScreen.setScene(exitScene);
		homeScreen.show();
		
		
		instructions = new WebView();
		
		WebEngine engine = instructions.getEngine();
		File temp = new File("about.html");
		String url = "file:///" + temp.getAbsolutePath();
		engine.load(url);
		okay = new Button("OK");
		okay.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if((Button)(event.getSource()) == okay) {
					power.setScene(scene);
				}
				
			}
			
		});
		
		VBox rect = new VBox();
		rect.setAlignment(Pos.CENTER);
		rect.getChildren().addAll(instructions, okay);
		howTo = new Scene(rect);
		
// SCENE AND SHOW STAGE
		scene = new Scene(root);
		stage.setTitle("Minesweeper"); 
		stage.setScene(scene);
		stage.setHeight(400);
		stage.setWidth(300);
		//stage.show();
		
		
		
	}
	
	
	public class MenuHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			if((MenuItem)(event.getSource()) == beg) {
				rows = 8;
				cols = 8;
				model = new P7_Kaushik_Prisha_MinesweeperModel(rows, cols, 10);
				run = true;
				first = true;
				
				board.getChildren().clear();
				board = newBoard(rows, cols);
				root.setCenter(board);
				
				timer.stop();
				seconds = 0;
				timeNum.setText("" + seconds);
				mineNum.setText("" + model.getNumFlagsLeft());
				//timer.start();
				smile.setImage(smileFace);
				
				updateView();
				
			} else if((MenuItem)(event.getSource()) == abt) {
				about.show();
			} else if((MenuItem)(event.getSource()) == exit) {
				run = false;
				if(square.getChildren().size() == 2) {
					square.getChildren().add(1, exited);
					square.getChildren().add(2, revert);
				}
				homeScreen.show();
				power.close();
			} else if((MenuItem)(event.getSource()) == inter) {
				rows = 16;
				cols = 16;
			
				model = new P7_Kaushik_Prisha_MinesweeperModel(rows, cols, 40);
				
				run = true;
				first = true;
				
				board.getChildren().clear();
				board = newBoard(rows, cols);
				root.setCenter(board);
				//scene = new Scene(root);
				
				timer.stop();
				seconds = 0;
				timeNum.setText("" + seconds);
				mineNum.setText("" + model.getNumFlagsLeft());
				//timer.start();
				smile.setImage(smileFace);
				
				updateView();
			} else if((MenuItem)(event.getSource()) == adv) {
				rows = 16;
				cols = 31;
			
				model = new P7_Kaushik_Prisha_MinesweeperModel(rows, cols, 99);
				
				run = true;
				first = true;
				
				board.getChildren().clear();
				board = newBoard(rows, cols);
				root.setCenter(board);
				//scene = new Scene(root);
				
				timer.stop();
				seconds = 0;
				timeNum.setText("" + seconds);
				mineNum.setText("" + model.getNumFlagsLeft());
				//timer.start();
				smile.setImage(smileFace);
				
				updateView();
			} else if((MenuItem)(event.getSource()) == numMines) {
				
				//board.getChildren().clear();
				
				TextInputDialog input = new TextInputDialog();  
				input.setHeaderText("How many mines would you like?");  
				input.showAndWait();  
				int ans = Integer.parseInt(input.getEditor().getText()); 
				if(ans <= 0 || ans > (model.getNumCols()*model.getNumRows() - 1)) {
					Alert a = new Alert(AlertType.ERROR, "Please enter a valid number of mines.", ButtonType.OK);
					a.showAndWait();
				} else {
					run = true;
					first = true;
					
					model = new P7_Kaushik_Prisha_MinesweeperModel(rows, cols, ans);
					updateView();
					
					timer.stop();
					seconds = 0;
					timeNum.setText("" + seconds);
					mineNum.setText("" + model.getNumFlagsLeft());
					//timer.start();
					smile.setImage(smileFace);
				}
			}  else if((MenuItem)(event.getSource()) == gridSize) {
				run = true;
				first = true;
				
				TextInputDialog input = new TextInputDialog();  
				input.setHeaderText("How many rows would you like?");  
				input.showAndWait();  
				int row = Integer.parseInt(input.getEditor().getText()); 
				
				TextInputDialog input1 = new TextInputDialog();  
				input1.setHeaderText("How many columns would you like?");  
				input1.showAndWait();  
				int col = Integer.parseInt(input.getEditor().getText()); 
				
				int m = model.numTotalMines();
				if(m >= (row*col)) {
					m = 0;
				}
				rows = row;
				cols = col;
				model = new P7_Kaushik_Prisha_MinesweeperModel(rows, cols, m);
				board.getChildren().clear();
				board = newBoard(rows, cols);
				root.setCenter(board);
				
				updateView();
				
				timer.stop();
				seconds = 0;
				timeNum.setText("" + seconds);
				mineNum.setText("" + model.getNumFlagsLeft());
				//timer.start();
				smile.setImage(smileFace);
			} else if((MenuItem)(event.getSource()) == custom) {
				
				
				TextInputDialog input = new TextInputDialog();  
				input.setHeaderText("How many rows would you like?");  
				input.showAndWait();  
				int row = Integer.parseInt(input.getEditor().getText()); 
				
				TextInputDialog input1 = new TextInputDialog();  
				input1.setHeaderText("How many columns would you like?");  
				input1.showAndWait();  
				int col = Integer.parseInt(input1.getEditor().getText()); 
				
				
				
				TextInputDialog input0 = new TextInputDialog();  
				input0.setHeaderText("How many mines would you like?");  
				input0.showAndWait();  
				int mines = Integer.parseInt(input0.getEditor().getText()); 
				
				if(mines <= 0 || mines > (row*col - 1)) {
					Alert a = new Alert(AlertType.ERROR, "Please enter a valid number of mines.", ButtonType.OK);
					a.showAndWait();
				} else {
					rows = row;
					cols = col;
					
					run = true;
					first = true;
					
					model = new P7_Kaushik_Prisha_MinesweeperModel(rows, cols, mines);
					board.getChildren().clear();
					board = newBoard(rows, cols);
					root.setCenter(board);
					
					updateView();
					
					timer.stop();
					seconds = 0;
					timeNum.setText("" + seconds);
					mineNum.setText("" + model.getNumFlagsLeft());
					//timer.start();
					smile.setImage(smileFace);
				}
			
			} else if((MenuItem)(event.getSource()) == howPlay) {
				power.setScene(howTo);
			}
			
		}
		
	}
	
	
	
	public class Handler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			if(run) {
				ImageView source = (ImageView)(event.getSource());
				int row = board.getRowIndex(source);
				int col = board.getColumnIndex(source);
				
				if(event.getButton() == MouseButton.SECONDARY) {
					model.toggleFlag(row, col);
					mineNum.setText("" + model.getNumFlagsLeft());
					
				} else {
					if(model.isMine(row, col) && !model.isFlagged(row, col)) { 
						updateMineView(row, col);
						run = false;
					} else if(!model.isRevealed(row, col) && !model.isFlagged(row, col)) {
						if(first) {
							timer.start();
							model.generateField(row, col);
							first = false;
							
						}
						model.revealCell(row, col);
						
					}
					
				}
				updateView();
				
			}
			
			
		}
		
	}
	
	
	public void updateView() {
		if(run) {
			for(int i = 0; i < model.getNumRows(); i++) {
				for(int j = 0; j < model.getNumCols(); j++) {
					ImageView tile = getTile(i, j);
					if(!model.isRevealed(i, j)) {
						if(model.isFlagged(i, j)) {
							tile.setImage(flagTile);
						} else {
							tile.setImage(blankTile);
						}
					} else {
						if(model.isFlagged(i, j)) {
							
							if(tile.getImage() != flagTile) {
								FadeTransition fade = new FadeTransition();
								fade.setFromValue(0.5);
								fade.setToValue(1);
								fade.setDuration(new Duration(100));
								fade.setCycleCount(1);
								fade.setNode(tile);
								fade.play();
							}
							
							tile.setImage(flagTile);
						} else if(model.numBorderMines(i, j) == 0) {
							if(tile.getImage() != num0) {
								FadeTransition fade = new FadeTransition();
								fade.setFromValue(0.5);
								fade.setToValue(1);
								fade.setDuration(new Duration(100));
								fade.setCycleCount(1);
								fade.setNode(tile);
								fade.play();
							}
							tile.setImage(num0);
						} else if(model.numBorderMines(i, j) == 1) {
							
							if(tile.getImage() != num1) {
								FadeTransition fade = new FadeTransition();
								fade.setFromValue(0.5);
								fade.setToValue(1);
								fade.setDuration(new Duration(100));
								fade.setCycleCount(1);
								fade.setNode(tile);
								fade.play();
							}
							
							tile.setImage(num1);
						} else if(model.numBorderMines(i, j) == 2) {
							if(tile.getImage() != num2) {
								FadeTransition fade = new FadeTransition();
								fade.setFromValue(0.5);
								fade.setToValue(1);
								fade.setDuration(new Duration(100));
								fade.setCycleCount(1);
								fade.setNode(tile);
								fade.play();
							}
							tile.setImage(num2);
						} else if(model.numBorderMines(i, j) == 3) {
							
							if(tile.getImage() != num3) {
								FadeTransition fade = new FadeTransition();
								fade.setFromValue(0.5);
								fade.setToValue(1);
								fade.setDuration(new Duration(100));
								fade.setCycleCount(1);
								fade.setNode(tile);
								fade.play();
							}
							
							tile.setImage(num3);
						} else if(model.numBorderMines(i, j) == 4) {
							
							if(tile.getImage() != num4) {
								FadeTransition fade = new FadeTransition();
								fade.setFromValue(0.5);
								fade.setToValue(1);
								fade.setDuration(new Duration(100));
								fade.setCycleCount(1);
								fade.setNode(tile);
								fade.play();
							}
							
							tile.setImage(num4);
						} else if(model.numBorderMines(i, j) == 5) {
							
							if(tile.getImage() != num5) {
								FadeTransition fade = new FadeTransition();
								fade.setFromValue(0.5);
								fade.setToValue(1);
								fade.setDuration(new Duration(100));
								fade.setCycleCount(1);
								fade.setNode(tile);
								fade.play();
							}
							
							tile.setImage(num5);
						} else if(model.numBorderMines(i, j) == 6) {
							
							if(tile.getImage() != num6) {
								FadeTransition fade = new FadeTransition();
								fade.setFromValue(0.5);
								fade.setToValue(1);
								fade.setDuration(new Duration(100));
								fade.setCycleCount(1);
								fade.setNode(tile);
								fade.play();
							}
							
							tile.setImage(num6);
						} else if(model.numBorderMines(i, j) == 7) {
							
							if(tile.getImage() != num7) {
								FadeTransition fade = new FadeTransition();
								fade.setFromValue(0.5);
								fade.setToValue(1);
								fade.setDuration(new Duration(100));
								fade.setCycleCount(1);
								fade.setNode(tile);
								fade.play();
							}
							
							tile.setImage(num7);
						} else if(model.numBorderMines(i, j) == 8) {
							
							if(tile.getImage() != num8) {
								FadeTransition fade = new FadeTransition();
								fade.setFromValue(0.5);
								fade.setToValue(1);
								fade.setDuration(new Duration(100));
								fade.setCycleCount(1);
								fade.setNode(tile);
								fade.play();
							}
							
							tile.setImage(num8);
						}
					}
				}
			}
			
			if(model.allCellsExposed()) {
				smile.setImage(winFace);
				run = false;
				timer.stop();
				
				Alert a = new Alert(AlertType.INFORMATION, "You Won!", ButtonType.OK);
				a.showAndWait();
				
			}
		}
	}
	
	
	
	
	public ImageView getTile(int row, int col) {
		for(int i = 0; i < board.getChildren().size(); i++) {
			Node tile = board.getChildren().get(i);
			if(GridPane.getColumnIndex(tile) == col && GridPane.getRowIndex(tile) == row) {
				return (ImageView)(tile);
			}
		}
		
		return null;
	}
	
	
	public GridPane newBoard(int rows, int cols) {
		GridPane temp = new GridPane();
			for(int i = 0; i < rows; i++) {
				for(int j = 0; j < cols; j++) {
					ImageView obj = new ImageView(blankTile);
					temp.add(obj, j, i);
					obj.setOnMouseClicked(handler);
					obj.setOnMouseEntered(fx);
					obj.setOnMouseExited(fx);
				}
			}
		temp.setAlignment(Pos.CENTER);
		return temp;
	}
	
	
	
	public void updateMineView(int row, int col) {
		//int[][] arr = new int[model.numTotalMines() - model.getNumFlagsLeft()][model.numTotalMines() - model.getNumFlagsLeft()];
		
		model.revealAllMines();
		timer.stop();
		run = false;
		for(int i = 0; i < model.getNumRows(); i++) {
			for(int j = 0; j < model.getNumCols(); j++) {
				if(i == row && col == j) {
					ImageView tile = getTile(i, j);
					FadeTransition fade = new FadeTransition();
					fade.setFromValue(0.5);
					fade.setToValue(1);
					fade.setDuration(new Duration(100));
					fade.setCycleCount(1);
					fade.setNode(tile);
					fade.play();
					
					tile.setImage(bombDead);
				} else if (model.isMine(i, j) && !model.isFlagged(i, j)){
					ImageView tile = getTile(i, j);
					
					FadeTransition fade = new FadeTransition();
					fade.setFromValue(0.5);
					fade.setToValue(1);
					fade.setDuration(new Duration(100));
					fade.setCycleCount(1);
					fade.setNode(tile);
					fade.play();
					
					tile.setImage(bomb);
				}
			}
		}
		
		smile.setImage(deadFace);
		Alert a = new Alert(AlertType.INFORMATION, "You Lost!", ButtonType.OK);
		a.showAndWait();
	}
	
	
	public class EffectHandler implements EventHandler<MouseEvent>{

		@Override
		public void handle(MouseEvent event) {
			if(run) {
				if(event.getEventType() == MouseEvent.MOUSE_ENTERED) {
					Lighting light = new Lighting();
					((Node) event.getSource()).setEffect(light);
				} else if(event.getEventType() == MouseEvent.MOUSE_EXITED) {
					((Node) event.getSource()).setEffect(null);
				}
			} else {
				((Node) event.getSource()).setEffect(null);
			}
			
		}
		
	}
		
	
}
