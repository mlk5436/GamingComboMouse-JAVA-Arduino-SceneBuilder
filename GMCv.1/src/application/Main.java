package application;
	
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import application.games.gameViewController;
import application.newgame.newGamePageController;
import application.view.toolbarController;
import application.view.gameMenuController;
import application.view.settingController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;


public class Main extends Application {
	
	private Stage primaryStage;
	private BorderPane mainLayout;
	private BorderPane mainItems;
	private Button[] games;
	private int numberOfGame;

	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("GMCv1.0");
		this.primaryStage.setResizable(false);
		
		showMainView();

	}
	
	public BorderPane getMainItems() {
		return mainItems;
	}


	public void setMainItems(BorderPane mainItems) {
		this.mainItems = mainItems;	

	}
	
	public void showMainView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/MainView.fxml"));
			mainLayout = loader.load();
			toolbarController controller = loader.getController();
			controller.setMain(this);
			Scene scene = new Scene(mainLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
			showMainItems();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void showMainItems() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("games/gamesMenu.fxml"));
			
			setMainItems(loader.load());
			
			gameMenuController controller = loader.getController();
			controller.setMain(this);
			mainLayout.setCenter(getMainItems());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void showNewGameSetUpPage() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("newgame/newgamesetup.fxml"));
			BorderPane newGame= loader.load();
			newGamePageController controller = loader.getController();
			controller.setMain(this);
			mainLayout.setCenter(newGame);
		}catch(IOException e) {e.printStackTrace();}
		
	}
	public void showGameScene() {
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("games/gameView.fxml"));
			BorderPane newGame = loader.load();
			gameViewController controller = loader.getController();
			controller.setMain(this);
			mainLayout.setCenter(newGame);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void showSettingPage() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/setting.fxml"));
			BorderPane setting = loader.load();
			
			settingController controller = loader.getController();
			controller.setMain(this);
			controller.getports();
			mainLayout.setCenter(setting);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void showHelpPage() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/help.fxml"));
			BorderPane help = loader.load();
			mainLayout.setCenter(help);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
public static void main(String[] args) {
		launch(args);
	}



	
}