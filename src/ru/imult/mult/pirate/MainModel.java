package ru.imult.mult.pirate;

import java.util.Map;

import com.google.gson.annotations.SerializedName;

public class MainModel {
	   //private boolean error; // им€ должно повтор€ть им€ json-пол€. ≈сли нет, то делаем так
	   private Data data;
	   private Info info;
	   //private boolean whatever;
	   private String status;
	   //private Lesson lessons;
	  /* public MainModel() {
		  

	   }*/
	   public Data getData() {
	       return data;
	   }
	   public Info getInfo() {
	       return info;
	   }
	   public String getStatus() {
	       return status;
	   }
	   
}
