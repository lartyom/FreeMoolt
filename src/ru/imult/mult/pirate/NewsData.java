package ru.imult.mult.pirate;

import java.util.List;
import java.util.Map;

public class NewsData {
private String action;
private long fromdate;
private int id;
private Map<String,String> params;
private String text;
private String title;
private String type;

public String getAction() {
    return action;
}
public long getFromdate() {
    return fromdate;
}
public int getID() {
    return id;
}
public Map<String, String> getParams() {
    return params;
}
public String getText() {
    return text;
}
public String getTitle() {
    return title;
}
public String getType() {
    return type;
}
}