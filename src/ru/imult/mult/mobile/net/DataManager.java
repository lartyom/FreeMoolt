package ru.imult.mult.mobile.net;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class DataManager {
	 private static DataManager instance;
	private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String GET = "GET";
    public static DataManager getInstance()
    {
        if (instance == null)
            instance = new DataManager();
        return instance;
    }
	 public JsonObject getJsonFromUrlSync(final String url) throws IOException
	    {
	        URL r_url = new URL(url);
	        HttpURLConnection connection = (HttpURLConnection) r_url.openConnection();
	        // add auth header to request
	        connection.connect();
	        InputStream in;
	        int status = connection.getResponseCode();
	        if (status != HttpURLConnection.HTTP_OK) {
	            in = connection.getErrorStream();
	        } else {
	            in = connection.getInputStream();
	        }
	        String response = convertStreamToString(in);
		    JsonObject jsonobject;
		    jsonobject = (new JsonParser()).parse(response).getAsJsonObject();
		    return jsonobject;
	    }

	private String convertStreamToString(InputStream stream) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        stream.close();

        return sb.toString();
	}
	 
	    }


