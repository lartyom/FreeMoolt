package ru.imult.mult.pirate;

import java.util.List;

public class EpisodeMainModel {
	 private List<EpisodeData> data;
	   private Info info;
	   //private boolean whatever;
	   private String status;
	   //private Lesson lessons;
	  /* public MainModel() {


	   }*/
	   public List<EpisodeData> getData() {
	       return data;
	   }
	   public Info getInfo() {
	       return info;
	   }
	   public String getStatus() {
	       return status;
	   }
}