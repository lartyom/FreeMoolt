package ru.imult.mult.pirate;
import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import ru.imult.mult.mobile.m3u8.PlaylistParser;
import com.google.gson.JsonObject;
import ru.imult.mult.mobile.net.DataManager;
import ru.imult.mult.mobile.util.Utils;
import ru.imult.mult.mobile.m3u8.Element;
import ru.imult.mult.mobile.m3u8.M3u8Parser;
import ru.imult.mult.mobile.m3u8.ParseException;
public class Main {
	
	 public static void main(String[] args) throws IOException, ParseException, InterruptedException  {
		 /*String s = "https://mult.digitala.ru/api/v1/main_page";
		 JsonObject jsonobject = DataManager.getInstance().getJsonFromUrlSync(s);
		 Object aobj[] = new Object[2];
	        aobj[0] = s;
	        aobj[1] = Utils.beautifyJson(jsonobject);*/
     //System.out.printf("Episode update %s: %s", aobj);
	     
       	ArrayList<Element> plist = new ArrayList<Element>();
   //new PlaylistParser(ru.imult.mult.mobile.m3u8.PlaylistType.M3U8).parse(fr);
		 M3u8Parser.createPlaylist(new PlaylistParser(ru.imult.mult.mobile.m3u8.PlaylistType.M3U8).parse( new FileReader(new File(args[0]/*"C:\\Users\\Admin\\Downloads\\21428.480p.m3u8"*/))),args[1]);
		 String[] command =
			    {
			        "cmd",
			    };
			    Process p = Runtime.getRuntime().exec(command);
			    new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
			    new Thread(new SyncPipe(p.getInputStream(), System.out)).start();
			    PrintWriter stdin = new PrintWriter(p.getOutputStream());
			    stdin.println("cd "+args[1]);
			    stdin.println("ffmpeg -f concat -i stream.txt -c copy all.ts");
			    // write any other commands you want here
			    stdin.close();
			    int returnCode = p.waitFor();
			    System.out.println("Return code = " + returnCode);
			    System.out.println("Removing segments...");
	            //создаем объект FileReader для объекта File
	            FileReader fr = new FileReader( new File(args[1]+"\\stream.txt"/*"ffmpeg-4.2.2-win64-static\\bin\\stream.txt"*/));
	            //создаем BufferedReader с существующего FileReader для построчного считывания
	            BufferedReader reader = new BufferedReader(fr);
	            // считаем сначала первую строку
	            String line;
	            int counter = 0;
	            while ((line=reader.readLine()) != null) {
	            	new File(args[1]+"\\"+line.split(" ")[1]).delete();
	            	  System.out.println("Remove "+line.split(" ")[1]+" segment.");
	            }
	            }
		 //proc.exec("ffmpeg -f concat -i stream.txt -c copy all.ts");
       
	 }

