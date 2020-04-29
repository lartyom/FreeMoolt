package ru.imult.mult.mobile.m3u8;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.concurrent.CountDownLatch;
import java.util.Iterator;
public class M3u8Parser {
	public static interface OnParsedListener
    {

        public abstract void onError(Exception exception);

        public abstract void onParsed(QualityPlaylist qualityplaylist);
    }
	 /*public static void Read(String file_path)
	    {
		 try
	        {
			    File file = new File(file_path);
	            //создаем объект FileReader для объекта File
	            FileReader fr = new FileReader(file);
	            //создаем BufferedReader с существующего FileReader для построчного считывания
	            BufferedReader reader = new BufferedReader(fr);
	            // считаем сначала первую строку
	            String line;
	            int counter = 0;
	            while ((line=reader.readLine()) != null) {
	                //System.out.println(line);
	                // считываем остальные строки в цикле
	                //line = reader.readLine();
	            
	                String[] user_input = String.valueOf((char)c).split(":");
	                switch(user_input[0]){
	                case "#EXTINF":
	                	reader.
	                	break;
	                }   
	                if(line.contains("video")){
	                	 counter++;
	                	URL url = new URL("https://b1.mult.digitala.ru/c/"+line);
	                	ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());
	                	 FileOutputStream fos = new FileOutputStream("output_"+String.valueOf(counter)+".ts");         
	                	 fos.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
	                	 fos.close();
	                	 readableByteChannel.close();
	                }
	            }
	           
	        } catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }*/
	 public static void createPlaylist(Playlist playlist, String ffmpegpath/*, OnParsedListener onparsedlistener*/) throws MalformedURLException, IOException
	    {
		 QualityPlaylist qualityplaylist = new QualityPlaylist();
	     qualityplaylist.setMaster(playlist);
		 //CountDownLatch countdownlatch = new CountDownLatch(playlist.getElements().size());
	     Element element;
	     String s;
	     try(PrintStream printStream = new PrintStream(ffmpegpath+"\\stream.txt"))
	        {
	    for(int i =0;i<playlist.getElements().size();i++){    	
	    
	    	 s = (new StringBuilder()).append("https://b1.mult.digitala.ru/c/").append(((Element)playlist.getElements().get(i)).getURI().toString()).toString();
	    	 System.out.println("Downloading "+s+" file...");
	    	 ReadableByteChannel readableByteChannel = Channels.newChannel(new URL(s).openStream());
        	 FileOutputStream fos = new FileOutputStream(ffmpegpath+"\\output_"+String.valueOf(i)+".ts");        
        	 fos.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
        	 fos.close();
        	 readableByteChannel.close();
	    	printStream.println("file output_"+String.valueOf(i)+".ts");
	    }
	        } catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     
	    }
	    }

