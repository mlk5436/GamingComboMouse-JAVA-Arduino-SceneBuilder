package application.view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.fazecast.jSerialComm.SerialPort;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class settingController {
	@FXML ComboBox<String> comboBox;
	
	private Main main;
	public void setMain(Main main) {
		this.main = main;
	}
	
	@FXML
	public void getports() {
		SerialPort[] portNames = SerialPort.getCommPorts();
		for(int i = 0; i < portNames.length; i++)
			comboBox.getItems().add(portNames[i].getSystemPortName());
	}
	
	@FXML
	public void savePortName() throws IOException {
		String portName =comboBox.getSelectionModel().getSelectedItem().toString();
		File devicePort = new File("deviceportname.txt");
		FileWriter writer = new FileWriter(devicePort);
		writer.write(portName);
		writer.flush();
		writer.close();
	}

}
