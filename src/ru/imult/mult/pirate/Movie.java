package ru.imult.mult.pirate;

import java.util.Map;

public class Movie {
private String banner_url;
private String description;
private String duration;
private int episodes_count;
private int id;
private String lang;
private Map<String, String> links;
private int made_in_russia;
private String thumbnail_url;
private String title;
private String trailer_image_url;
private String trailer_url;
private int year;
public String getBannerURL() {
	return banner_url;
}
public String getDescription() {
	return description;
}
public String getDuration() {
	return duration;
}
public int getEpisodesCount() {
	return episodes_count;
}

public int getId() {
	return id;
}

public String getLang() {
	return lang;
}
public Map<String, String> getLinks() {
	return links;
}

public int getMadeInRussia() {
	return made_in_russia;
}

public String getThumbnailURL() {
	return thumbnail_url;
}
public String getTitle() {
	return title;
}
public String getTrailerImageURL() {
	return trailer_image_url;
}
public String getTrailerURL() {
	return trailer_url;
}
public int getYear() {
	return year;
}


}
