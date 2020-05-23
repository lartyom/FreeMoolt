package ru.imult.mult.pirate;

import java.util.List;
import java.util.Map;

public class EpisodeData {
	private int duration;
	private List<String> android_purchases;
	private String original_title;
	private String season;
	private String movie_id;
	private int number;
	private int is_popular;
	private int is_free;
	private int id;
	private String aus_id;
	private String movie_title;
	private String file_url;
	private String thumbnail_url;
	private String title;
	private String lang;
	private Map<String, Long> qualities;
	public int getDuration() {
		return duration;
	}
	public List<String> getAndroidPurchases() {
		return android_purchases;
	}
	public String getOriginalTitle() {
		return original_title;
	}
	public String getSeason() {
		return season;
	}

	public String getMovieID() {
		return movie_id;
	}

	public int getNumber() {
		return number;
	}
	public int getIsPopular() {
		return is_popular;
	}

	public int getIsFree() {
		return is_free;
	}

	public int getID() {
		return id;
	}
	public String getAusID() {
		return aus_id;
	}
	public String getMovieTitle() {
		return movie_title;
	}
	public String getFileURL() {
		return file_url;
	}
	public String getThumbnailURL() {
		return thumbnail_url;
	}
	public String getTitle() {
		return title;
	}
	public String getLang() {
		return lang;
	}
	public Map<String, Long> getQualities() {
		return qualities;
	}

}
