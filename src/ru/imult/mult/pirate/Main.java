package ru.imult.mult.pirate;
import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import ru.imult.mult.mobile.m3u8.PlaylistParser;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ru.imult.mult.mobile.net.DataManager;
import ru.imult.mult.mobile.util.Utils;
import ru.imult.mult.mobile.m3u8.Element;
import ru.imult.mult.mobile.m3u8.M3u8Parser;
import ru.imult.mult.mobile.m3u8.ParseException;
import ru.imult.mult.mobile.m3u8.Playlist;
public class Main {
	static boolean flag = true;
	static void downloadVideo(String playlist_url, String ffmpegpath) throws IOException, InterruptedException, ParseException{
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
			    stdin.println("ffmpeg -y -f concat -i stream.txt -c copy all.ts");
			    // write any other commands you want here
			    stdin.close();
			    int returnCode = p.waitFor();
			    System.out.println("Return code = " + returnCode);
			    deleteFiles(ffmpegpath, "stream.txt");
	}
	static void deleteFiles(String files_path, String files_list) throws IOException{
		System.out.println("Removing segments...");
        //создаем объект FileReader для объекта File
        FileReader fr = new FileReader( new File(files_path+"\\"+files_list/*"ffmpeg-4.2.2-win64-static\\bin\\stream.txt"*/));
        //создаем BufferedReader с существующего FileReader для построчного считывания
        BufferedReader reader = new BufferedReader(fr);
        // считаем сначала первую строку
        String line;
        int counter = 0;
        while ((line=reader.readLine()) != null) {
        	new File(files_path+"\\"+line.split(" ")[1]).delete();
        	  System.out.println("Remove "+line.split(" ")[1]+" segment.");
        }
        }

	 public static void main(String[] args) throws IOException, ParseException, InterruptedException  {
		 String s = "https://mult.digitala.ru/api/v1/main_page";
		 JsonObject jsonobject = DataManager.getInstance().getJsonFromUrlSync(s);
		 //System.out.println(jsonobject);
		 MainModel mainModel = new Gson().fromJson(jsonobject, MainModel.class);
		
		 //System.out.println(mainModel.getData());
		 System.out.println("FreeMoolt v.0.2 Copyleft (ɔ) 2020 norecord ");
		 System.out.println("Type \"download\" <type> <id> for download video.");
		 System.out.println("-------------------------------------------");
		 System.out.println(mainModel.getData().getBanner().getTitle());
		 System.out.println(new Date(mainModel.getData().getBanner().getFromdate()*1000).toString());
		 System.out.println("===========================================");
		 switch(args[1]){
		 case "foreign_movies":
		 System.out.println("=-=-=-=-=-=-=Foreign Movies-=-=-=-=-=-=-=-=");
		 for(Movie i : mainModel.getData().getForeignMovies()){
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
		 for(Movie i : mainModel.getData().getNewMovies()){
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
		 case "recomended_movies":
		 System.out.println("=-=-=-=-=-=-Recomend Movies=-=-=-=-=-=-=");
		 for(Movie i : mainModel.getData().getRecomendedMovies()){
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
		 while (flag){
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
		 }
		  //Utils.beautifyJson(jsonobject);
		  
     //System.out.printf("Episode update %s: %s", aobj);
	     
   
       	
		 //M3u8Parser.createPlaylist(new PlaylistParser(ru.imult.mult.mobile.m3u8.PlaylistType.M3U8).parse( new FileReader(new File(args[0]/*"C:\\Users\\Admin\\Downloads\\21428.480p.m3u8"*/))),args[1]);
		
	 }
}
	 

