package application.view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.FlowPane;

public class gameMenuController {
	
	@FXML private FlowPane flowpane;
	
	private Main main;
	public void setMain(Main main) {
		this.main = main;
	}
	
	@FXML
	public void ButtonHandler(ActionEvent event)throws IOException, InterruptedException{		
		String Name = ((Button)event.getSource()).getText();
		
		File currentGame = new File("currentgame.txt");
		FileWriter w = new FileWriter(currentGame);
		w.write(Name);
		w.flush();
		w.close();
		
		String upper = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"\r\n" + 
				"<?import javafx.scene.effect.*?>\r\n" + 
				"<?import javafx.geometry.*?>\r\n" + 
				"<?import javafx.scene.text.*?>\r\n" + 
				"<?import javafx.scene.control.*?>\r\n" + 
				"<?import java.lang.*?>\r\n" + 
				"<?import javafx.scene.layout.*?>\r\n" + 
				"<?import javafx.scene.layout.BorderPane?>\r\n" + 
				"\r\n" + 
				"<BorderPane xmlns=\"http://javafx.com/javafx/8\" xmlns:fx=\"http://javafx.com/fxml/1\" fx:controller=\"application.games.gameViewController\">\r\n" + 
				"   <center>\r\n" + 
				"      <BorderPane prefHeight=\"400.0\" prefWidth=\"600.0\" BorderPane.alignment=\"CENTER\">\r\n" + 
				"         <center>\r\n" + 
				"            <SplitPane prefHeight=\"160.0\" prefWidth=\"200.0\" BorderPane.alignment=\"CENTER\">\r\n" + 
				"               <items>\r\n" + 
				"                  <TabPane fx:id=\"tabPane\" prefHeight=\"200.0\" prefWidth=\"200.0\" tabClosingPolicy=\"UNAVAILABLE\">\r\n" + 
				"                    <tabs>\n";
		String lower = "      </tabs>\r\n" + 
				"                  </TabPane>\r\n" + 
				"               </items>\r\n" + 
				"            </SplitPane>\r\n" + 
				"         </center>\r\n" + 
				"         <top>\r\n" + 
				"            <HBox prefHeight=\"39.0\" prefWidth=\"600.0\" BorderPane.alignment=\"CENTER\">\r\n" + 
				"               <children>\r\n" + 
				"                  <Label prefHeight=\"35.0\" prefWidth=\"503.0\" text=\""+Name+"\">\r\n" + 
				"                     <HBox.margin>\r\n" + 
				"                        <Insets left=\"10.0\" />\r\n" + 
				"                     </HBox.margin>\r\n" + 
				"                     <font>\r\n" + 
				"                        <Font name=\"System Bold\" size=\"24.0\" />\r\n" + 
				"                     </font>\r\n" + 
				"                  </Label>\r\n" + 
				"                  <Button fx:id=\"LoadCombo\" onAction=\"#LoadComboHandler\" mnemonicParsing=\"false\" prefHeight=\"51.0\" prefWidth=\"147.0\" text=\"Load Combo\" />\r\n" + 
				"               </children>\r\n" + 
				"            </HBox>\r\n" + 
				"         </top>\r\n" + 
				"      </BorderPane>\r\n" + 
				"   </center>\r\n" + 
				"</BorderPane>\n";
		File gameView = new File("src/application/games/gameView.fxml");
		FileWriter gameViewWriter = new FileWriter(gameView);
		gameViewWriter.write(upper);
		gameViewWriter.flush();
		
		File combo = new File(Name.replaceAll("\\s","")+"Combos.txt");
		Scanner scanCombo = new Scanner(combo);
		int counter = 0;
		if (scanCombo.hasNextLine()) {
			while(scanCombo.hasNextLine()) {
				String[] tokens = scanCombo.nextLine().split(",");
				String comboKeys = "";
				for(int i = 2; i < tokens.length;i++) {
					if(i != tokens.length-1)
						comboKeys += tokens[i]+",";
					else
						comboKeys += tokens[i];
				}
				String tab = "<Tab text=\""+tokens[1]+"\">\r\n" + 
						"                           <content>\r\n" + 
						"                              <BorderPane prefHeight=\"200.0\" prefWidth=\"200.0\">\r\n" + 
						"                                 <center>\r\n" + 
						"                                    <GridPane maxHeight=\"-Infinity\" prefHeight=\"131.0\" prefWidth=\"424.0\" BorderPane.alignment=\"CENTER\">\r\n" + 
						"                                      <columnConstraints>\r\n" + 
						"                                        <ColumnConstraints hgrow=\"SOMETIMES\" maxWidth=\"295.6019287109375\" minWidth=\"10.0\" prefWidth=\"212.06504821777344\" />\r\n" + 
						"                                        <ColumnConstraints hgrow=\"SOMETIMES\" maxWidth=\"429.40138244628906\" minWidth=\"10.0\" prefWidth=\"385.93495178222656\" />\r\n" + 
						"                                      </columnConstraints>\r\n" + 
						"                                      <rowConstraints>\r\n" + 
						"                                        <RowConstraints minHeight=\"10.0\" prefHeight=\"30.0\" vgrow=\"SOMETIMES\" />\r\n" + 
						"                                        <RowConstraints minHeight=\"10.0\" prefHeight=\"30.0\" vgrow=\"SOMETIMES\" />\r\n" + 
						"                                          <RowConstraints minHeight=\"10.0\" prefHeight=\"30.0\" vgrow=\"SOMETIMES\" />\r\n" + 
						"                                      </rowConstraints>\r\n" + 
						"                                       <children>\r\n" + 
						"                                          <Label alignment=\"CENTER_RIGHT\" contentDisplay=\"CENTER\" prefHeight=\"75.0\" prefWidth=\"304.0\" text=\"Combo Name: \" />\r\n" + 
						"                                          <TextField text=\""+tokens[1]+"\" promptText=\"ComboName\" id=\"ComboName"+Integer.toString(counter)+"\" fx:id=\"ComboName"+Integer.toString(counter)+"\" maxWidth=\"-Infinity\" minWidth=\"-Infinity\" prefWidth=\"330.0\" GridPane.columnIndex=\"1\">\r\n" + 
						"                                             <GridPane.margin>\r\n" + 
						"                                                <Insets left=\"20.0\" />\r\n" + 
						"                                             </GridPane.margin>\r\n" + 
						"                                          </TextField>\r\n" + 
						"                                          <Label alignment=\"CENTER_RIGHT\" contentDisplay=\"CENTER\" prefHeight=\"69.0\" prefWidth=\"304.0\" text=\"Keys and Intervals: \" GridPane.rowIndex=\"1\" />\r\n" + 
						"                                          <TextField text=\""+comboKeys+"\" promptText=\"ComboKeys\" id=\"ComboKeys"+Integer.toString(counter)+"\" fx:id=\"ComboKeys"+Integer.toString(counter)+"\" maxWidth=\"-Infinity\" minWidth=\"-Infinity\" prefWidth=\"330.0\" GridPane.columnIndex=\"1\" GridPane.rowIndex=\"1\">\r\n" + 
						"                                             <GridPane.margin>\r\n" + 
						"                                                <Insets left=\"20.0\" />\r\n" + 
						"                                             </GridPane.margin>\r\n" + 
						"                                          </TextField>\r\n" + 
						"                                          <Button onAction=\"#SaveComboHandler\" fx:id=\"SaveCombo"+Integer.toString(counter)+"\" maxWidth=\"-Infinity\" minHeight=\"-Infinity\" mnemonicParsing=\"false\" prefWidth=\"330.0\" text=\"Save Combo\" GridPane.columnIndex=\"1\" GridPane.rowIndex=\"2\">\r\n" + 
						"                                             <GridPane.margin>\r\n" + 
						"                                                <Insets left=\"20.0\" />\r\n" + 
						"                                             </GridPane.margin>\r\n" + 
						"                                          </Button>\r\n" + 
						"                                       </children>\r\n" + 
						"                                    </GridPane>\r\n" + 
						"                                 </center>\r\n" + 
						"                              </BorderPane>\r\n" + 
						"                           </content>\r\n" + 
						"                      </Tab>\n";
				
				
				gameViewWriter.write(tab);
				gameViewWriter.flush();
				counter++;
			}
			for (int i = counter; i < 11; i ++) {
				String tab = " <Tab text=\"Combo "+ Integer.toString(i)+"\">\r\n" + 
						"                           <content>\r\n" + 
						"                              <BorderPane prefHeight=\"200.0\" prefWidth=\"200.0\">\r\n" + 
						"                                 <center>\r\n" + 
						"                                    <GridPane maxHeight=\"-Infinity\" prefHeight=\"131.0\" prefWidth=\"424.0\" BorderPane.alignment=\"CENTER\">\r\n" + 
						"                                      <columnConstraints>\r\n" + 
						"                                        <ColumnConstraints hgrow=\"SOMETIMES\" maxWidth=\"295.6019287109375\" minWidth=\"10.0\" prefWidth=\"212.06504821777344\" />\r\n" + 
						"                                        <ColumnConstraints hgrow=\"SOMETIMES\" maxWidth=\"429.40138244628906\" minWidth=\"10.0\" prefWidth=\"385.93495178222656\" />\r\n" + 
						"                                      </columnConstraints>\r\n" + 
						"                                      <rowConstraints>\r\n" + 
						"                                        <RowConstraints minHeight=\"10.0\" prefHeight=\"30.0\" vgrow=\"SOMETIMES\" />\r\n" + 
						"                                        <RowConstraints minHeight=\"10.0\" prefHeight=\"30.0\" vgrow=\"SOMETIMES\" />\r\n" + 
						"                                          <RowConstraints minHeight=\"10.0\" prefHeight=\"30.0\" vgrow=\"SOMETIMES\" />\r\n" + 
						"                                      </rowConstraints>\r\n" + 
						"                                       <children>\r\n" + 
						"                                          <Label alignment=\"CENTER_RIGHT\" contentDisplay=\"CENTER\" prefHeight=\"75.0\" prefWidth=\"304.0\" text=\"Combo Name: \" />\r\n" + 
						"                                          <TextField text=\"\" promptText=\"ComboName\" id=\"ComboName"+Integer.toString(i)+"\" fx:id=\"ComboName"+Integer.toString(i)+"\" maxWidth=\"-Infinity\" minWidth=\"-Infinity\" prefWidth=\"330.0\" GridPane.columnIndex=\"1\">\r\n" + 
						"                                             <GridPane.margin>\r\n" + 
						"                                                <Insets left=\"20.0\" />\r\n" + 
						"                                             </GridPane.margin>\r\n" + 
						"                                          </TextField>\r\n" + 
						"                                          <Label alignment=\"CENTER_RIGHT\" contentDisplay=\"CENTER\" prefHeight=\"69.0\" prefWidth=\"304.0\" text=\"Keys and Intervals: \" GridPane.rowIndex=\"1\" />\r\n" + 
						"                                          <TextField text=\"\" promptText=\"ComboKeys\" id=\"ComboKeys"+Integer.toString(i)+"\" fx:id=\"ComboKeys"+Integer.toString(i)+"\" maxWidth=\"-Infinity\" minWidth=\"-Infinity\" prefWidth=\"330.0\" GridPane.columnIndex=\"1\" GridPane.rowIndex=\"1\">\r\n" + 
						"                                             <GridPane.margin>\r\n" + 
						"                                                <Insets left=\"20.0\" />\r\n" + 
						"                                             </GridPane.margin>\r\n" + 
						"                                          </TextField>\r\n" + 
						"                                          <Button onAction=\"#SaveComboHandler\" fx:id=\"SaveCombo"+Integer.toString(i)+"\" maxWidth=\"-Infinity\" minHeight=\"-Infinity\" mnemonicParsing=\"false\" prefWidth=\"330.0\" text=\"Save Combo\" GridPane.columnIndex=\"1\" GridPane.rowIndex=\"2\">\r\n" + 
						"                                             <GridPane.margin>\r\n" + 
						"                                                <Insets left=\"20.0\" />\r\n" + 
						"                                             </GridPane.margin>\r\n" + 
						"                                          </Button>\r\n" + 
						"                                       </children>\r\n" + 
						"                                    </GridPane>\r\n" + 
						"                                 </center>\r\n" + 
						"                              </BorderPane>\r\n" + 
						"                           </content>\r\n" + 
						"                      </Tab>\n";
				
				gameViewWriter.write(tab);
				gameViewWriter.flush();
			}
		}
		else {
			for (int i = 1; i < 11; i ++) {
				String tab = " <Tab text=\"Combo "+ Integer.toString(i)+"\">\r\n" + 
						"                           <content>\r\n" + 
						"                              <BorderPane prefHeight=\"200.0\" prefWidth=\"200.0\">\r\n" + 
						"                                 <center>\r\n" + 
						"                                    <GridPane maxHeight=\"-Infinity\" prefHeight=\"131.0\" prefWidth=\"424.0\" BorderPane.alignment=\"CENTER\">\r\n" + 
						"                                      <columnConstraints>\r\n" + 
						"                                        <ColumnConstraints hgrow=\"SOMETIMES\" maxWidth=\"295.6019287109375\" minWidth=\"10.0\" prefWidth=\"212.06504821777344\" />\r\n" + 
						"                                        <ColumnConstraints hgrow=\"SOMETIMES\" maxWidth=\"429.40138244628906\" minWidth=\"10.0\" prefWidth=\"385.93495178222656\" />\r\n" + 
						"                                      </columnConstraints>\r\n" + 
						"                                      <rowConstraints>\r\n" + 
						"                                        <RowConstraints minHeight=\"10.0\" prefHeight=\"30.0\" vgrow=\"SOMETIMES\" />\r\n" + 
						"                                        <RowConstraints minHeight=\"10.0\" prefHeight=\"30.0\" vgrow=\"SOMETIMES\" />\r\n" + 
						"                                          <RowConstraints minHeight=\"10.0\" prefHeight=\"30.0\" vgrow=\"SOMETIMES\" />\r\n" + 
						"                                      </rowConstraints>\r\n" + 
						"                                       <children>\r\n" + 
						"                                          <Label alignment=\"CENTER_RIGHT\" contentDisplay=\"CENTER\" prefHeight=\"75.0\" prefWidth=\"304.0\" text=\"Combo Name: \" />\r\n" + 
						"                                          <TextField text=\"\" promptText=\"ComboName\" id=\"ComboName"+Integer.toString(i)+"\" fx:id=\"ComboName"+Integer.toString(i)+"\" maxWidth=\"-Infinity\" minWidth=\"-Infinity\" prefWidth=\"330.0\" GridPane.columnIndex=\"1\">\r\n" + 
						"                                             <GridPane.margin>\r\n" + 
						"                                                <Insets left=\"20.0\" />\r\n" + 
						"                                             </GridPane.margin>\r\n" + 
						"                                          </TextField>\r\n" + 
						"                                          <Label alignment=\"CENTER_RIGHT\" contentDisplay=\"CENTER\" prefHeight=\"69.0\" prefWidth=\"304.0\" text=\"Keys and Intervals: \" GridPane.rowIndex=\"1\" />\r\n" + 
						"                                          <TextField text=\"\" promptText=\"ComboKeys\" id=\"ComboKeys"+Integer.toString(i)+"\" fx:id=\"ComboKeys"+Integer.toString(i)+"\" maxWidth=\"-Infinity\" minWidth=\"-Infinity\" prefWidth=\"330.0\" GridPane.columnIndex=\"1\" GridPane.rowIndex=\"1\">\r\n" + 
						"                                             <GridPane.margin>\r\n" + 
						"                                                <Insets left=\"20.0\" />\r\n" + 
						"                                             </GridPane.margin>\r\n" + 
						"                                          </TextField>\r\n" + 
						"                                          <Button onAction=\"#SaveComboHandler\" fx:id=\"SaveCombo"+Integer.toString(i)+"\" maxWidth=\"-Infinity\" minHeight=\"-Infinity\" mnemonicParsing=\"false\" prefWidth=\"330.0\" text=\"Save Combo\" GridPane.columnIndex=\"1\" GridPane.rowIndex=\"2\">\r\n" + 
						"                                             <GridPane.margin>\r\n" + 
						"                                                <Insets left=\"20.0\" />\r\n" + 
						"                                             </GridPane.margin>\r\n" + 
						"                                          </Button>\r\n" + 
						"                                       </children>\r\n" + 
						"                                    </GridPane>\r\n" + 
						"                                 </center>\r\n" + 
						"                              </BorderPane>\r\n" + 
						"                           </content>\r\n" + 
						"                      </Tab>\n";
				
				gameViewWriter.write(tab);
				gameViewWriter.flush();
			}
			
		}
		
		gameViewWriter.flush();
		gameViewWriter.write(lower);
		gameViewWriter.flush();
		gameViewWriter.close();
		
		TimeUnit.SECONDS.sleep(4);
		
		main.showGameScene();
			
	}

}
