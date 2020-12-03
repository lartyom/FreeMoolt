package ru.imult.mult.pirate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
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
		 alert.show();

    }
    @FXML
    private void aboutInfo(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setHeaderText("FreeMoolt v.1.2");
    	alert.setGraphic(new ImageView(this.getClass().getResource("icon.png").toString()));
    	alert.setContentText("");
    	alert.show();

    }
    @FXML
    private void downloadTrailer(ActionEvent event) throws IOException, InterruptedException, ParseException {
    	//if(Main.cartoon_info[2]!=""){
    	Main.downloadVideo(Main.cartoon_info[2] , Main.ffmpegpath+"\\bin","trailer.mp4" );
    	/*}else{
    		new Alert(AlertType.WARNING,"Select movie").show();;
    	}*/

      }
    
    @FXML
    private void sort(ActionEvent event) {
    	Alert dialog = new Alert(AlertType.NONE);
    	dialog.setHeaderText("Сортировка");
    	 RadioButton sort_1 = new RadioButton("Подряд");
    	 sort_1.selectedProperty();
         RadioButton sort_2 = new RadioButton("Обратный порядок");
         RadioButton sort_3 = new RadioButton("Новые первыми");
          sort_1.setSelected(true);
         ToggleGroup group = new ToggleGroup();
         // установка группы
         sort_1.setToggleGroup(group);
         sort_2.setToggleGroup(group);
         sort_3.setToggleGroup(group);

         // установка обработчика события нажатия
         sort_1.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event)
             {
            	Main.order[0]="episode_number";
            	Main.order[1]="asc";
            	System.out.println("order_by: "+Main.order[0]);
            	System.out.println("order_direction: "+Main.order[1]);
             }
             });
         sort_2.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event)
             {
            	Main.order[0]="episode_number";
            	Main.order[1]="desc";
            	System.out.println("order_by: "+Main.order[0]);
            	System.out.println("order_direction: "+Main.order[1]);

             }
             });
         sort_3.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event)
             {
            	Main.order[0]="published_at";
            	Main.order[1]="asc";
            	System.out.println("order_by: "+Main.order[0]);
            	System.out.println("order_direction: "+Main.order[1]);
             }
             });
         VBox root = new VBox();
         root.getChildren().addAll(sort_1, sort_2, sort_3);
         dialog.getDialogPane().setContent(root);
         dialog.getDialogPane().getButtonTypes().add(new ButtonType("Выбрать",ButtonData.OK_DONE));
    	dialog.showAndWait();
    }
    @FXML
    private void settings(ActionEvent event) {
    	
    	
    	Alert dialog = new Alert(AlertType.NONE);
    	dialog.setHeaderText("Настройки");
    	GridPane grid = new GridPane();
    	grid.setHgap(10);
    	grid.setVgap(10);
    	grid.setPadding(new Insets(20, 150, 10, 10));

    	TextField ffmpeg_path = new TextField();
    	CheckBox autorun_1 = new CheckBox("Автозапуск видео");
    	Button set_path = new Button("set");
    	ObservableList<String> qualities = FXCollections.observableArrayList("480p", "360p", "240p", "144p");
        ChoiceBox<String> quality = new ChoiceBox<String>(qualities);
        ObservableList<String> servers = FXCollections.observableArrayList("b1.mult.digitala.ru", "youtube.com");
        ChoiceBox<String> server = new ChoiceBox<String>(servers);
        
    	ffmpeg_path.setPromptText(Main.ffmpegpath);
    	grid.add(new Label("FFmpeg path:"), 0, 0);
    	grid.add(ffmpeg_path, 1, 0);
    	grid.add(set_path, 2, 0);
    	grid.add(new Label("Качество видео при загрузке:"), 0, 1);
    	grid.add(quality, 1, 1);
    	grid.add(new Label("Видеосервер:"), 0, 2);
    	grid.add(server, 1, 2);
    	
    	grid.add(autorun_1, 1, 3);
    	quality.setValue(Main.video_quality);
    	server.setValue(Main.video_server);
    	quality.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
            	Main.video_quality=quality.getValue();
            }
    	});
    	server.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
            	Main.video_server=server.getValue();
            }
    	});
    	autorun_1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
            	if(Main.autorun!=true){
            	Main.autorun=true;
            	}else{
            		Main.autorun=false;
            	}
            	}
            });
    	if(Main.autorun==true){
    		autorun_1.setSelected(true);
    	}
    	System.out.println(Main.autorun);
        set_path.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event){
        	Main.ffmpegpath=ffmpeg_path.getText();
        }
        });
    	//VBox root = new VBox();
    	 //root.setPadding(new Insets(10));
    	 //root.getChildren().addAll(autorun_1);
    	 dialog.getDialogPane().setContent(grid);
    	 dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
    	dialog.showAndWait();
    }
    }




