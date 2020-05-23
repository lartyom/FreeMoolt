package ru.imult.mult.pirate;
import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import ru.imult.mult.mobile.m3u8.PlaylistParser;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.imult.mult.mobile.net.DataManager;
import ru.imult.mult.mobile.util.Utils;
import ru.imult.mult.mobile.m3u8.Element;
import ru.imult.mult.mobile.m3u8.M3u8Parser;
import ru.imult.mult.mobile.m3u8.ParseException;
import ru.imult.mult.mobile.m3u8.Playlist;
public class Main extends Application{
	static boolean flag = true;
	static String[] cartoon_info = new String[3];
	//static String _args;
	public static void downloadVideo(String playlist_url, String ffmpegpath, String videoname) throws IOException, InterruptedException, ParseException{
		M3u8Parser.createPlaylist(DataManager.getInstance().getM3U8FromUrlSync(playlist_url),ffmpegpath);
		 String[] command =
			    {
			        "cmd",
			    };
			    Process p = Runtime.getRuntime().exec(command);
			    new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
			    new Thread(new SyncPipe(p.getInputStream(), System.out)).start();
			    PrintWriter stdin = new PrintWriter(p.getOutputStream());
			    stdin.println("cd "+ffmpegpath);
			    stdin.println("ffmpeg -y -f concat -i stream.txt -c copy "+videoname);
			    // write any other commands you want here
			    stdin.close();
			    int returnCode = p.waitFor();
			    System.out.println("Return code = " + returnCode);
			    deleteFiles(ffmpegpath, "stream.txt");
	}
	static void deleteFiles(String files_path, String files_list) throws IOException{
		System.out.println("Removing segments...");
        //СЃРѕР·РґР°РµРј РѕР±СЉРµРєС‚ FileReader РґР»СЏ РѕР±СЉРµРєС‚Р° File
        FileReader fr = new FileReader( new File(files_path+"\\"+files_list/*"ffmpeg-4.2.2-win64-static\\bin\\stream.txt"*/));
        //СЃРѕР·РґР°РµРј BufferedReader СЃ СЃСѓС‰РµСЃС‚РІСѓСЋС‰РµРіРѕ FileReader РґР»СЏ РїРѕСЃС‚СЂРѕС‡РЅРѕРіРѕ СЃС‡РёС‚С‹РІР°РЅРёСЏ
        BufferedReader reader = new BufferedReader(fr);
        // СЃС‡РёС‚Р°РµРј СЃРЅР°С‡Р°Р»Р° РїРµСЂРІСѓСЋ СЃС‚СЂРѕРєСѓ
        String line;
        int counter = 0;
        while ((line=reader.readLine()) != null) {
        	new File(files_path+"\\"+line.split(" ")[1]).delete();
        	  System.out.println("Remove "+line.split(" ")[1]+" segment.");
        }
        }

	 public static void main(String[] args) throws IOException, ParseException, InterruptedException  {
		 Application.launch(args);
		
		 
	    /* System.out.println(new Date(mainModel.getInfo().getServerTime()*1000).toString());
	     System.out.println(mainModel.getInfo().getSelf());
		 System.out.println(mainModel.getStatus());*/
		/* while (flag){
		 Scanner in = new Scanner(System.in);
		 String[] user_input =  in.nextLine().split(" ");
		 switch (user_input[0]) {
		case "download":
			switch(user_input[1]){
			case "foreign_movies":
			 for(Movie i : mainModel.getData().getForeignMovies()){
				 if(i.getId()==Integer.parseInt(user_input[2])){
					 //System.out.println(i.getTitle());
					 downloadVideo(i.getTrailerURL(),args[0]);
			 }
			 }
				 break;
				 case "new_movies":
					 for(Movie i : mainModel.getData().getNewMovies()){
						 if(i.getId()==Integer.parseInt(user_input[2])){
							 //System.out.println(i.getTitle());
							 downloadVideo(i.getTrailerURL(),args[0]);
						 }
					 }
					 break;
				 case "recomended_movies":
					 for(Movie i : mainModel.getData().getRecomendedMovies()){
						 if(i.getId()==Integer.parseInt(user_input[2])){
							 //System.out.println(i.getTitle());
							 downloadVideo(i.getTrailerURL(),args[0]);
						 }
					 }
					 break;
			 }			 
			 
			break;
		case "exit":
			flag = false;
			break;

		default:
			 System.out.println("Command not found.");
			break;
		}
		 }*/
		  //Utils.beautifyJson(jsonobject);
		  
     //System.out.printf("Episode update %s: %s", aobj);
	     
   
       	
		 //M3u8Parser.createPlaylist(new PlaylistParser(ru.imult.mult.mobile.m3u8.PlaylistType.M3U8).parse( new FileReader(new File(args[0]/*"C:\\Users\\Admin\\Downloads\\21428.480p.m3u8"*/))),args[1]);
		
	 }

@Override
public void start(Stage stage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("Main.fxml")); 
    Scene scene = new Scene(root);
     
    String s = "https://mult.digitala.ru/api/v1/main_page";
	 JsonObject jsonobject = DataManager.getInstance().getJsonFromUrlSync(s);
	 //System.out.println(jsonobject);
	 MainModel mainModel = new Gson().fromJson(jsonobject, MainModel.class);
	 System.out.println("FreeMoolt v.0.5 Copyleft (ɔ) 2020 norecord");
	 System.out.println("Type \"download\" <type> <id> for download video.");
	 System.out.println("-------------------------------------------");
	 System.out.println(mainModel.getData().getBanner().getTitle());
	 System.out.println(new Date(mainModel.getData().getBanner().getFromdate()*1000).toString());
	 System.out.println("===========================================");
	ImageView banner = (ImageView) root.getChildrenUnmodifiable().get(0);
	FlowPane movies = (FlowPane) root.getChildrenUnmodifiable().get(3);
	Label movies_name = (Label) root.getChildrenUnmodifiable().get(2);
	 //System.out.println(mainModel.getData());
	
	 //System.out.println(mainModel.getData().getBanner().getTitle());
	 banner.setImage(new Image(mainModel.getData().getBanner().getImageURL()));
	 //System.out.println(new Date(mainModel.getData().getBanner().getFromdate()*1000).toString());
	 switch(getParameters().getUnnamed().get(0)){
	 case "recomended_movies":
		 System.out.println("=-=-=-=-=-=-Recomend Movies=-=-=-=-=-=-=");
	 for(Movie i : mainModel.getData().getRecomendedMovies()){
		 movies_name.setText("Рекомендуемые мультики");
		 ImageView movie_image = new ImageView(new Image(i.getThumbnailURL()));
		 movie_image.setFitHeight(150);
		 movie_image.setFitWidth(100);
		 movie_image.setPreserveRatio(true);
		 Button movie_name = new Button(i.getTitle());
         movie_name.setOnAction(new EventHandler<ActionEvent>() {
             
             @Override
             public void handle(ActionEvent event) {
              
                 banner.setImage(new Image(i.getBannerURL())); 
                 cartoon_info[0] = i.getDescription();
                 cartoon_info[1] = i.getTitle();
                 cartoon_info[2] = i.getTrailerURL();
                 movies_name.setText("Серии");
                 movies.getChildren().clear();
                 EpisodeMainModel episode_mainModel;
				try {
					episode_mainModel = new Gson().fromJson(DataManager.getInstance().getJsonFromUrlSync("https://mult.digitala.ru/api/v1/materials?type=episode&order_by=episode_number&order_direction=asc&movie_id="+i.getId()), EpisodeMainModel.class);
					 for(EpisodeData i : episode_mainModel.getData()){
	            		 
	            		 ImageView movie_image = new ImageView(new Image(i.getThumbnailURL()));
	            		 movie_image.setFitHeight(150);
	            		 movie_image.setFitWidth(100);
	            		 movie_image.setPreserveRatio(true);
	            		 Button movie_name = new Button(i.getTitle());
	            		   movie_name.setOnAction(new EventHandler<ActionEvent>() {
	            	             
	            	             @Override
	            	             public void handle(ActionEvent event) {
	            	            	 try {
										M3u8Parser.createPlaylistOfPlaylist(DataManager.getInstance().getM3U8FromUrlSync(i.getFileURL()), "ffmpeg-4.2.2-win64-static\\bin");
									} catch (IOException | ParseException | InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
	            	                
	            	             }
	            	         });	                         					              
	            		 movie_name.setPrefWidth(100);
	            		 VBox movie = new VBox(movie_image,movie_name);
	            		 movies.getChildren().add(movie);
	             }
					 
				} catch (JsonSyntaxException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
         }
             });
		 movie_name.setPrefWidth(100);
		 
		 VBox movie = new VBox(movie_image,movie_name);
		 movies.getChildren().add(movie);
		 System.out.println(i.getTitle());
		 System.out.println("---------------------------------------");
		 System.out.println(i.getDescription());
		 System.out.println("ID: "+i.getId());
		 System.out.println("Episodes: "+i.getEpisodesCount());
		 System.out.println("Duration: "+i.getDuration());
		 System.out.println("Trailer: "+i.getTrailerURL());
		 System.out.println("Language: "+i.getLang());
		 if(i.getMadeInRussia() != 0){
		 	System.out.println("Made in Russia");
		 }
		 System.out.println("---------------------------------------"); 
	 }
	 break;
	 case "new_movies":
	 System.out.println("=-=-=-=-=-=-=-=New Movies-=-=-=-=-=-=-=-=");
	 movies_name.setText("Новые мультики");
	 for(Movie i : mainModel.getData().getNewMovies()){
		 ImageView movie_image = new ImageView(new Image(i.getThumbnailURL()));
		 movie_image.setFitHeight(150);
		 movie_image.setFitWidth(100);
		 movie_image.setPreserveRatio(true);
		 Button movie_name = new Button(i.getTitle());
		 movie_name.setPrefWidth(100);
         movie_name.setOnAction(new EventHandler<ActionEvent>() {
             
             @Override
             public void handle(ActionEvent event) {
              
                 banner.setImage(new Image(i.getBannerURL())); 
                 cartoon_info[0] = i.getDescription();
                 cartoon_info[1] = i.getTitle();
                 cartoon_info[2] = i.getTrailerURL();
                 movies.getChildren().clear();
             }
         });
		 VBox movie = new VBox(movie_image,movie_name);
		 movies.getChildren().add(movie);
		 System.out.println(i.getTitle());
		 System.out.println("---------------------------------------");
		 System.out.println(i.getDescription());
		 System.out.println("ID: "+i.getId());
		 System.out.println("Episodes: "+i.getEpisodesCount());
		 System.out.println("Duration: "+i.getDuration());
		 System.out.println("Trailer: "+i.getTrailerURL());
		 System.out.println("Language: "+i.getLang());
		 if(i.getMadeInRussia() != 0){
			 System.out.println("Made in Russia");
		 }
		 System.out.println("---------------------------------------"); 
	 }
	 break;
	 case "foreign_movies":
	 System.out.println("=-=-=-=-=-=-=Foreign Movies-=-=-=-=-=-=-=-=");
	 movies_name.setText("Зарубежные мультики");
	 for(Movie i : mainModel.getData().getForeignMovies()){
		 ImageView movie_image = new ImageView(new Image(i.getThumbnailURL()));
		 movie_image.setFitHeight(150);
		 movie_image.setFitWidth(100);
		 movie_image.setPreserveRatio(true);
		 Button movie_name = new Button(i.getTitle());
		 movie_name.setPrefWidth(100);
 movie_name.setOnAction(new EventHandler<ActionEvent>() {
             
             @Override
             public void handle(ActionEvent event) {
              
                 banner.setImage(new Image(i.getBannerURL())); 
                 cartoon_info[0] = i.getDescription();
                 cartoon_info[1] = i.getTitle();
                 cartoon_info[2] = i.getTrailerURL();
                 movies.getChildren().clear();
                 
             }
         });
		 VBox movie = new VBox(movie_image,movie_name);
		 movies.getChildren().add(movie);
		 System.out.println(i.getTitle());
		 System.out.println("---------------------------------------");
		 System.out.println(i.getDescription());
		 System.out.println("ID: "+i.getId());
		 System.out.println("Episodes: "+i.getEpisodesCount());
		 System.out.println("Duration: "+i.getDuration());
		 System.out.println("Trailer: "+i.getTrailerURL());
		 System.out.println("Language: "+i.getLang());
		 if(i.getMadeInRussia() != 0){
			 System.out.println("Made in Russia");
		 }
		 System.out.println("----------------------------------------"); 
	 }
	 break;
	 }
	   System.out.println(new Date(mainModel.getInfo().getServerTime()*1000).toString());
	     System.out.println(mainModel.getInfo().getSelf());
		 System.out.println(mainModel.getStatus());
    
    stage.setScene(scene);
    stage.setResizable(false);
    stage.setTitle("FreeMoolt");
    stage.setWidth(700);
    stage.setHeight(850);
     
    stage.show();
}
}

	 

