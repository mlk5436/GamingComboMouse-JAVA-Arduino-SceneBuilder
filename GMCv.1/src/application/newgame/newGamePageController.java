package application.newgame;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.CharBuffer;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import application.Main;
import application.games.gameViewController;
import application.view.gameMenuController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class newGamePageController {
	
	@FXML private TextField name;
	
	
	private Main main;

	public void setMain(Main main) {
		this.main = main;
	}
	
	@FXML
	public void create() throws Exception{
		
	
		//System.out.println("button works");
		String Name = name.getText();
		File games = new File("games.txt");
		FileWriter writer = new FileWriter(games, true);
		writer.write(Name+"\n");
		writer.flush();
		writer.close();
		
//		writeAdditionButton(Name);
		writegameMenuFxml(Name);
		createCombosFile(Name);
		
		TimeUnit.SECONDS.sleep(3);
		main.showMainView();

	}

	public void createCombosFile(String Name) throws IOException {
		File data = new File(Name.replaceAll("\\s","")+"Combos.txt");
		FileWriter datawriter = new FileWriter(data);
		datawriter.write("");
		datawriter.flush();
		datawriter.close();
	}
	

	
	public void writeAdditionButton(String Name) throws IOException {
		// add new game's name to games.txt
				File games = new File("games.txt");
				FileWriter writer = new FileWriter(games, true);
				writer.write(Name+"\n");
				writer.flush();
				writer.close();
				
				
		File gameMenuController = new File("src/application/view/gameMenuController.java");
		
		FileWriter gameMenuControllerWriter = new FileWriter(gameMenuController);
		
		String upperGameMenuController = "package application.view;\r\n" + 
				"\r\n" + 
				"import java.io.File;\r\n" + 
				"import java.io.FileWriter;\r\n" + 
				"import java.io.IOException;\r\n" + 
				"\r\n" + 
				"import application.Main;\r\n" + 
				"import javafx.fxml.FXML;\r\n" + 
				"\r\n" + 
				"public class gameMenuController {\r\n" + 
				"	\r\n" + 
				"	private Main main;\r\n" + 
				"	public void setMain(Main main) {\r\n" + 
				"		this.main = main;\r\n" + 
				"	}\n";
		
		String lowerGameMenuController = "}\r\n"; 
		
		gameMenuControllerWriter.write(upperGameMenuController);
		
		// write a button handler for every game
		
		Scanner scanGame = new Scanner(games);
		while(scanGame.hasNextLine()) {
			String buttonFunction = "	@FXML\r\n" + 
					"	public void "+scanGame.nextLine().replaceAll("\\s","")+"Handler()throws IOException{\r\n" + 
					"		String tab = \"<Tab text=\\\"Untitled Tab 1\\\">\\r\\n\" + \r\n" + 
					"				\"                           <content>\\r\\n\" + \r\n" + 
					"				\"                              <BorderPane prefHeight=\\\"200.0\\\" prefWidth=\\\"200.0\\\">\\r\\n\" + \r\n" + 
					"				\"                                 <center>\\r\\n\" + \r\n" + 
					"				\"                                    <GridPane maxHeight=\\\"-Infinity\\\" prefHeight=\\\"131.0\\\" prefWidth=\\\"424.0\\\" BorderPane.alignment=\\\"CENTER\\\">\\r\\n\" + \r\n" + 
					"				\"                                      <columnConstraints>\\r\\n\" + \r\n" + 
					"				\"                                        <ColumnConstraints hgrow=\\\"SOMETIMES\\\" maxWidth=\\\"295.6019287109375\\\" minWidth=\\\"10.0\\\" prefWidth=\\\"212.06504821777344\\\" />\\r\\n\" + \r\n" + 
					"				\"                                        <ColumnConstraints hgrow=\\\"SOMETIMES\\\" maxWidth=\\\"429.40138244628906\\\" minWidth=\\\"10.0\\\" prefWidth=\\\"385.93495178222656\\\" />\\r\\n\" + \r\n" + 
					"				\"                                      </columnConstraints>\\r\\n\" + \r\n" + 
					"				\"                                      <rowConstraints>\\r\\n\" + \r\n" + 
					"				\"                                        <RowConstraints minHeight=\\\"10.0\\\" prefHeight=\\\"30.0\\\" vgrow=\\\"SOMETIMES\\\" />\\r\\n\" + \r\n" + 
					"				\"                                        <RowConstraints minHeight=\\\"10.0\\\" prefHeight=\\\"30.0\\\" vgrow=\\\"SOMETIMES\\\" />\\r\\n\" + \r\n" + 
					"				\"                                      </rowConstraints>\\r\\n\" + \r\n" + 
					"				\"                                       <children>\\r\\n\" + \r\n" + 
					"				\"                                          <Label alignment=\\\"CENTER_RIGHT\\\" contentDisplay=\\\"CENTER\\\" prefHeight=\\\"75.0\\\" prefWidth=\\\"304.0\\\" text=\\\"Combo Name: \\\" />\\r\\n\" + \r\n" + 
					"				\"                                          <TextField maxWidth=\\\"-Infinity\\\" minWidth=\\\"-Infinity\\\" prefWidth=\\\"330.0\\\" GridPane.columnIndex=\\\"1\\\">\\r\\n\" + \r\n" + 
					"				\"                                             <GridPane.margin>\\r\\n\" + \r\n" + 
					"				\"                                                <Insets left=\\\"20.0\\\" />\\r\\n\" + \r\n" + 
					"				\"                                             </GridPane.margin>\\r\\n\" + \r\n" + 
					"				\"                                          </TextField>\\r\\n\" + \r\n" + 
					"				\"                                          <Label alignment=\\\"CENTER_RIGHT\\\" contentDisplay=\\\"CENTER\\\" prefHeight=\\\"69.0\\\" prefWidth=\\\"304.0\\\" text=\\\"Keys and Intervals: \\\" GridPane.rowIndex=\\\"1\\\" />\\r\\n\" + \r\n" + 
					"				\"                                          <TextField maxWidth=\\\"-Infinity\\\" minWidth=\\\"-Infinity\\\" prefWidth=\\\"330.0\\\" GridPane.columnIndex=\\\"1\\\" GridPane.rowIndex=\\\"1\\\">\\r\\n\" + \r\n" + 
					"				\"                                             <GridPane.margin>\\r\\n\" + \r\n" + 
					"				\"                                                <Insets left=\\\"20.0\\\" />\\r\\n\" + \r\n" + 
					"				\"                                             </GridPane.margin>\\r\\n\" + \r\n" + 
					"				\"                                          </TextField>\\r\\n\" + \r\n" + 
					"				\"                                       </children>\\r\\n\" + \r\n" + 
					"				\"                                    </GridPane>\\r\\n\" + \r\n" + 
					"				\"                                 </center>\\r\\n\" + \r\n" + 
					"				\"                              </BorderPane>\\r\\n\" + \r\n" + 
					"				\"                           </content>\\r\\n\" + \r\n" + 
					"				\"                      </Tab>\";\r\n" + 
					"		String upper = \"<?xml version=\\\"1.0\\\" encoding=\\\"UTF-8\\\"?>\\r\\n\" + \r\n" + 
					"				\"\\r\\n\" + \r\n" + 
					"				\"<?import javafx.scene.effect.*?>\\r\\n\" + \r\n" + 
					"				\"<?import javafx.geometry.*?>\\r\\n\" + \r\n" + 
					"				\"<?import javafx.scene.text.*?>\\r\\n\" + \r\n" + 
					"				\"<?import javafx.scene.control.*?>\\r\\n\" + \r\n" + 
					"				\"<?import java.lang.*?>\\r\\n\" + \r\n" + 
					"				\"<?import javafx.scene.layout.*?>\\r\\n\" + \r\n" + 
					"				\"<?import javafx.scene.layout.BorderPane?>\\r\\n\" + \r\n" + 
					"				\"\\r\\n\" + \r\n" + 
					"				\"<BorderPane xmlns=\\\"http://javafx.com/javafx/8\\\" xmlns:fx=\\\"http://javafx.com/fxml/1\\\" fx:controller=\\\"application.games.gameViewController\\\">\\r\\n\" + \r\n" + 
					"				\"   <center>\\r\\n\" + \r\n" + 
					"				\"      <BorderPane prefHeight=\\\"400.0\\\" prefWidth=\\\"600.0\\\" BorderPane.alignment=\\\"CENTER\\\">\\r\\n\" + \r\n" + 
					"				\"         <center>\\r\\n\" + \r\n" + 
					"				\"            <SplitPane prefHeight=\\\"160.0\\\" prefWidth=\\\"200.0\\\" BorderPane.alignment=\\\"CENTER\\\">\\r\\n\" + \r\n" + 
					"				\"               <items>\\r\\n\" + \r\n" + 
					"				\"                  <TabPane prefHeight=\\\"200.0\\\" prefWidth=\\\"200.0\\\" tabClosingPolicy=\\\"UNAVAILABLE\\\">\\r\\n\" + \r\n" + 
					"				\"                    <tabs>\";\r\n" + 
					"		String lower = \" </tabs>\\r\\n\" + \r\n" + 
					"				\"                  </TabPane>\\r\\n\" + \r\n" + 
					"				\"               </items>\\r\\n\" + \r\n" + 
					"				\"            </SplitPane>\\r\\n\" + \r\n" + 
					"				\"         </center>\\r\\n\" + \r\n" + 
					"				\"         <top>\\r\\n\" + \r\n" + 
					"				\"            <HBox prefHeight=\\\"39.0\\\" prefWidth=\\\"600.0\\\" BorderPane.alignment=\\\"CENTER\\\">\\r\\n\" + \r\n" + 
					"				\"               <children>\\r\\n\" + \r\n" + 
					"				\"                  <Label prefHeight=\\\"35.0\\\" prefWidth=\\\"503.0\\\" text=\\\""+Name.replaceAll("\\s","")+"\\\">\\r\\n\" + \r\n" + 
					"				\"                     <HBox.margin>\\r\\n\" + \r\n" + 
					"				\"                        <Insets left=\\\"10.0\\\" />\\r\\n\" + \r\n" + 
					"				\"                     </HBox.margin>\\r\\n\" + \r\n" + 
					"				\"                     <font>\\r\\n\" + \r\n" + 
					"				\"                        <Font name=\\\"System Bold\\\" size=\\\"24.0\\\" />\\r\\n\" + \r\n" + 
					"				\"                     </font>\\r\\n\" + \r\n" + 
					"				\"                  </Label>\\r\\n\" + \r\n" + 
					"				\"                  <Button mnemonicParsing=\\\"false\\\" prefHeight=\\\"51.0\\\" prefWidth=\\\"147.0\\\" text=\\\"Add Combo\\\" />\\r\\n\" + \r\n" + 
					"				\"               </children>\\r\\n\" + \r\n" + 
					"				\"            </HBox>\\r\\n\" + \r\n" + 
					"				\"         </top>\\r\\n\" + \r\n" + 
					"				\"      </BorderPane>\\r\\n\" + \r\n" + 
					"				\"   </center>\\r\\n\" + \r\n" + 
					"				\"</BorderPane>\\r\\n\" + \r\n" + 
					"				\"\";\r\n" + 
					"		File gameView = new File(\"src/application/games/gameView.fxml\");\r\n" + 
					"		FileWriter gameViewWriter = new FileWriter(gameView);\r\n" + 
					"		gameViewWriter.write(upper);\r\n" + 
					"		gameViewWriter.write(lower);\r\n" + 
					"		gameViewWriter.flush();\r\n" + 
					"		gameViewWriter.close();\r\n" + 
					"		\r\n" + 
					"				main.showGameScene();\r\n" + 
					"	}\n";
			gameMenuControllerWriter.write(buttonFunction);
			gameMenuControllerWriter.flush();
			
		}
		scanGame.close();
		gameMenuControllerWriter.write(lowerGameMenuController);
		gameMenuControllerWriter.flush();
		gameMenuControllerWriter.close();
	}

	public void writegameMenuFxml(String Name) throws IOException {
		String upper = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"\r\n" + 
				"<?import javafx.scene.text.*?>\r\n" + 
				"<?import javafx.geometry.*?>\r\n" + 
				"<?import javafx.scene.control.*?>\r\n" + 
				"<?import java.lang.*?>\r\n" + 
				"<?import javafx.scene.layout.*?>\r\n" + 
				"<?import javafx.scene.layout.BorderPane?>\r\n" + 
				"\r\n" + 
				"<BorderPane maxHeight=\"-Infinity\" maxWidth=\"-Infinity\" minHeight=\"-Infinity\" minWidth=\"-Infinity\" prefHeight=\"480.0\" prefWidth=\"640.0\" xmlns=\"http://javafx.com/javafx/8\" xmlns:fx=\"http://javafx.com/fxml/1\" fx:controller=\"application.view.gameMenuController\">\r\n" + 
				"   <center>\r\n" + 
				"      <BorderPane prefHeight=\"400.0\" prefWidth=\"600.0\" BorderPane.alignment=\"CENTER\">\r\n" + 
				"         <top>\r\n" + 
				"            <FlowPane hgap=\"20.0\" maxHeight=\"-Infinity\" maxWidth=\"-Infinity\" minHeight=\"-Infinity\" minWidth=\"-Infinity\" prefHeight=\"400.0\" prefWidth=\"600.0\" vgap=\"20.0\" BorderPane.alignment=\"CENTER\">\r\n" + 
				"               <children>\r\n";
		String Lower = "</children>\r\n" + 
				"               <padding>\r\n" + 
				"                  <Insets left=\"150.0\" top=\"80.0\" />\r\n" + 
				"               </padding>\r\n" + 
				"            </FlowPane>\r\n" + 
				"         </top>\r\n" + 
				"      </BorderPane>\r\n" + 
				"   </center>\r\n" + 
				"</BorderPane>\r\n";
		//System.out.println(upper);
		
		// add number of game to numGame.txt
		File numGame = new File("numGame.txt");
		Scanner sc = new Scanner(numGame);
		int num = sc.nextInt();
		num++;
		FileWriter numWriter = new FileWriter(numGame);
		numWriter.write(Integer.toString(num));
		numWriter.flush();
		numWriter.close();
		sc.close();
	
		File games = new File("games.txt");
	
		
		// add new button to games.fxml
		
		File file = new File("src/application/games/gamesMenu.fxml");
		FileWriter fileWriter = new FileWriter(file);
		fileWriter.write(upper);
		fileWriter.flush();
		Scanner scanner = new Scanner(games);
				
		while(scanner.hasNextLine()) {
			String str = scanner.nextLine();
			//System.out.println(str);
			
			if (num > 2) 
				fileWriter.write("            		<Button maxHeight=\"-Infinity\" maxWidth=\"-Infinity\" minHeight=\"-Infinity\" minWidth=\"-Infinity\" mnemonicParsing=\"false\" onAction=\"#ButtonHandler\" prefHeight=\""+300.0/num+"\" prefWidth=\""+600.0/num+"\" text=\""+str+"\" />  \r\n");
			else
				fileWriter.write("            		<Button maxHeight=\"-Infinity\" maxWidth=\"-Infinity\" minHeight=\"-Infinity\" minWidth=\"-Infinity\" mnemonicParsing=\"false\" onAction=\"#ButtonHandler\" prefHeight=\""+150.0+"\" prefWidth=\""+300.0+"\" text=\""+str+"\" />  \r\n");
			fileWriter.flush();
		}
			scanner.close();
				fileWriter.write(Lower);
				fileWriter.flush();
				fileWriter.close();
	
				
	}
}
