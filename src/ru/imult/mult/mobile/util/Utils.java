package ru.imult.mult.mobile.util;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class Utils {
	 public static String beautifyJson(JsonObject jsonobject)
	    {
	        return (new GsonBuilder()).setPrettyPrinting().create().toJson(jsonobject);
	    }
}
