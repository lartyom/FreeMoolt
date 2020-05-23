package ru.imult.mult.pirate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import ru.imult.mult.mobile.m3u8.ParseException;
import ru.imult.mult.mobile.net.DataManager;
public class MainController {

    @FXML
    private Button btn;

    @FXML
    private ImageView banner;

    @FXML
    private void exit(ActionEvent event) {
       Platform.exit();
     }
    @FXML
    private void cartoonInfo(ActionEvent event) {
    //if(Main.cartoon_info[2]!=""){
       Alert alert = new Alert(AlertType.INFORMATION);
       DialogPane pane = alert.getDialogPane();
       pane.setPrefWidth(550.0);
       alert.setWidth(pane.getWidth());
       alert.setHeaderText(Main.cartoon_info[1]);
       alert.setContentText(Main.cartoon_info[0]);
       alert.show();
 /*   }else{
		new Alert(AlertType.WARNING,"Select movie").show();;
	}*/
      }
    @FXML
    private void showNews(ActionEvent event) throws IOException {
    	 String s = "https://mult.digitala.ru/api/v1/news";
		 JsonObject jsonobject = DataManager.getInstance().getJsonFromUrlSync(s);
		 //System.out.println(jsonobject);
		 NewsMainModel mainModel = new Gson().fromJson(jsonobject, NewsMainModel.class);

    	Alert alert = new Alert(AlertType.INFORMATION);
        //DialogPane pane = alert.getDialogPane();
        //pane.setPrefWidth(550.0);
        //alert.setWidth(pane.getWidth());
    	 alert.setHeaderText("News");
    	String news = new String();
    	for(NewsData i : mainModel.getData()){
			 news+=i.getTitle()+"\n";
			 news+="---------------------------------------\n";
			 news+=i.getText()+"\n";
			 news+="ID: "+i.getID()+"\n";
			 news+="Type: "+i.getType()+"\n";
			 news+="Action: "+i.getAction()+"\n";
			 news+="Date: "+(new Date(i.getFromdate()*1000).toString())+"\n";
			// System.out.println("Language: "+i.getLang());
			 news+="---------------------------------------\n";
		 }
		 TextArea textArea = new TextArea(news);
		 textArea.setEditable(false);
		 textArea.setWrapText(true);

		 textArea.setMaxWidth(Double.MAX_VALUE);
		 textArea.setMaxHeight(Double.MAX_VALUE);
		 GridPane.setVgrow(textArea, Priority.ALWAYS);
		 GridPane.setHgrow(textArea, Priority.ALWAYS);

		 GridPane expContent = new GridPane();
		 expContent.setMaxWidth(Double.MAX_VALUE);
		 //expContent.add(label, 0, 0);
		 expContent.add(textArea, 0, 1);

		 // Set expandable Exception into the dialog pane.
		 alert.getDialogPane().setContent(expContent);
		 alert.showAndWait();

    }
    @FXML
    private void aboutInfo(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setHeaderText("FreeMoolt v.0.5");
    	alert.setGraphic(new ImageView(this.getClass().getResource("icon.png").toString()));
    	alert.setContentText("This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 2 of the License, or (at your option) any later version. This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.");
    	alert.showAndWait();

    }
    @FXML
    private void downloadTrailer(ActionEvent event) throws IOException, InterruptedException, ParseException {
    	//if(Main.cartoon_info[2]!=""){
    	Main.downloadVideo(Main.cartoon_info[2] , "ffmpeg-4.2.2-win64-static\\bin","trailer.mp4" );
    	/*}else{
    		new Alert(AlertType.WARNING,"Select movie").show();;
    	}*/

      }




}