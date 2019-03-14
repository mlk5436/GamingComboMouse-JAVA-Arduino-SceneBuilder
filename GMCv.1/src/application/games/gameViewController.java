package application.games;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.fazecast.jSerialComm.SerialPort;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class gameViewController {
	
	@FXML private Button LoadCombo;
	
	@FXML private TextField ComboName1;
	@FXML private TextField ComboName2;
	@FXML private TextField ComboName3;
	@FXML private TextField ComboName4;
	@FXML private TextField ComboName5;
	@FXML private TextField ComboName6;
	@FXML private TextField ComboName7;
	@FXML private TextField ComboName8;
	@FXML private TextField ComboName9;
	@FXML private TextField ComboName10;

	@FXML private TextField ComboKeys1;
	@FXML private TextField ComboKeys2;
	@FXML private TextField ComboKeys3;
	@FXML private TextField ComboKeys4;
	@FXML private TextField ComboKeys5;
	@FXML private TextField ComboKeys6;
	@FXML private TextField ComboKeys7;
	@FXML private TextField ComboKeys8;
	@FXML private TextField ComboKeys9;
	@FXML private TextField ComboKeys10;
	
	static SerialPort chosenPort;
	private Main main;
	public void setMain(Main main) {
		this.main = main;
	}
	
	@FXML
	public void LoadComboHandler(ActionEvent event) throws IOException, InterruptedException {
		File deviceportname = new File("deviceportname.txt");
		Scanner scanport = new Scanner(deviceportname);
		File currentgame = new File("currentgame.txt");
		Scanner scanGame = new Scanner(currentgame);
		String gameName = scanGame.nextLine().replaceAll("\\s", "");
		scanGame.close();
		System.out.println(gameName);
		File gamecombofile= new File(gameName+"Combos.txt");
		Scanner scancombo = new Scanner(gamecombofile);
	
		if(scanport.hasNextLine()) {
			chosenPort = SerialPort.getCommPort(scanport.nextLine());
			chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
			if(chosenPort.openPort()) {
				
				// create a new thread for sending data to the arduino
				Thread thread = new Thread(){
					@Override public void run() {
						// wait after connecting, so the bootloader can finish
						try {Thread.sleep(100); } catch(Exception e) {}
							
						// enter an infinite loop that sends text to the arduino
						PrintWriter output = new PrintWriter(chosenPort.getOutputStream());
						while(scancombo.hasNextLine()) {
							output.print(scancombo.nextLine());
							output.flush();
							try {Thread.sleep(100); } catch(Exception e) {}

						}
					}
				};
				thread.start();
			}else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Error");
				alert.setHeaderText("Device Error");
				alert.setContentText("Can not connect to the device!");
				alert.showAndWait();
				
			}
			
		}else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Error");
			alert.setHeaderText("No Device Found");
			alert.setContentText("Please go to the setting and select your device!");
			alert.showAndWait();
		}
	}
	
	@FXML
	public void SaveComboHandler(ActionEvent event) throws IOException, InterruptedException {
		File currentgame = new File("currentgame.txt");
		Scanner scanGame = new Scanner(currentgame);
		String gameName = scanGame.nextLine().replaceAll("\\s", "");
		scanGame.close();
		String combofilepath = gameName+"Combos.txt";
		
		File combofile = new File(combofilepath);
		
		FileWriter write = new FileWriter(combofile, true);
		
		String combonum = ((Button)event.getSource()).getId();
		if (combonum.equals("SaveCombo1")) {
			if(ComboName1.getText()!= "" & ComboKeys1.getText() != "") {
				write.write("1,"+ComboName1.getText()+","+ComboKeys1.getText()+"\n");
				write.flush();
			}
			write.close();
		}
		
		if (combonum.equals("SaveCombo2")) {
			
			if(ComboName2.getText()!= "" & ComboKeys2.getText() != "") {
				
				write.write("2,"+ComboName2.getText()+","+ComboKeys2.getText()+"\n");
				write.flush();
			}
			write.close();
		}
		
		if (combonum.equals("SaveCombo3")) {
			if(ComboName3.getText()!= "" & ComboKeys3.getText() != "") {
				write.write("3,"+ComboName3.getText()+","+ComboKeys3.getText()+"\n");
				write.flush();
			}
			write.close();
		}
		
		if (combonum.equals("SaveCombo4")) {
			if(ComboName4.getText()!= "" & ComboKeys4.getText() != "") {
				write.write("4,"+ComboName4.getText()+","+ComboKeys4.getText()+"\n");
				write.flush();
			}
			write.close();
		}
		
		if (combonum.equals("SaveCombo5")) {
			if(ComboName5.getText()!= "" & ComboKeys5.getText() != "") {
				write.write("5,"+ComboName5.getText()+","+ComboKeys5.getText()+"\n");
				write.flush();
			}
			write.close();
		}
		
		if (combonum.equals("SaveCombo6")) {
			if(ComboName6.getText()!= "" & ComboKeys6.getText() != "") {
				write.write("6,"+ComboName6.getText()+","+ComboKeys6.getText()+"\n");
				write.flush();
			}
			write.close();
		}
		
		if (combonum.equals("SaveCombo7")) {
			if(ComboName7.getText()!= "" & ComboKeys7.getText() != "") {
				write.write("7,"+ComboName7.getText()+","+ComboKeys7.getText()+"\n");
				write.flush();
			}
			write.close();
		}
		
		if (combonum.equals("SaveCombo8")) {
			if(ComboName8.getText()!= "" & ComboKeys8.getText() != "") {
				write.write("8,"+ComboName8.getText()+","+ComboKeys8.getText()+"\n");
				write.flush();
			}
			write.close();
		}
		
		if (combonum.equals("SaveCombo9")) {
			if(ComboName9.getText()!= "" & ComboKeys9.getText() != "") {
				write.write("9,"+ComboName9.getText()+","+ComboKeys9.getText()+"\n");
				write.flush();
			}
			write.close();
		}
		
		if (combonum.equals("SaveCombo10")) {
			if(ComboName10.getText()!= "" & ComboKeys10.getText() != "") {
				write.write("10,"+ComboName10.getText()+","+ComboKeys10.getText()+"\n");
				write.flush();
			}
			write.close();
		}
				
			
	}
	
}
