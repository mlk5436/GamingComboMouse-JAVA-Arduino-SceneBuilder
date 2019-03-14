package application.view;

import java.io.FileWriter;
import java.io.IOException;

import application.Main;
import javafx.fxml.FXML;

public class toolbarController {
	
	private Main main;
	public void setMain(Main main) {
		this.main = main;
	}
	
	@FXML
	public void homeButtonHandler() throws IOException {
		
		main.showMainItems();
	}
	
	
	@FXML
	public void newGameButtonHandler()throws IOException{
		main.showNewGameSetUpPage();
	}
	
	@FXML
	public void settingButtonHandler()throws IOException{
		main.showSettingPage();
	}
	
	@FXML
	public void helpButtonHandler()throws IOException{
		main.showHelpPage();
	}
	
}
