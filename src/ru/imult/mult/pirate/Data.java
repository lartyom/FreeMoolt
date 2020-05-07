package ru.imult.mult.pirate;

import java.util.List;

public class Data {
	private Banner extension_banner;
	private List<Movie> foreign_movies;
	private List<Movie> new_movies;
	private List<Theme> new_themes;
	private List<Movie> recomended_movies;
	
	public Banner getBanner() {
	       return extension_banner;
	   }
	   public List<Movie> getForeignMovies() {
	       return foreign_movies;
	   }
	   public List<Movie> getNewMovies() {
	       return new_movies;
	   }
	   public List<Theme> getThemes() {
	       return new_themes;
	   }
	   public List<Movie> getRecomendedMovies() {
	       return recomended_movies;
	   }
}
