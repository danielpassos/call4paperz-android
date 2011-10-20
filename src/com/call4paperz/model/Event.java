package com.call4paperz.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class Event {

    private String name;
    private String description;
    private Date date;
    private String twitter;
    private String website;
    private String imageUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return name;
    }

    public Event fromJSON(JSONObject jsonObject) throws JSONException {

        name = jsonObject.getString("name");
        description = jsonObject.getString("description");
//        date =
        twitter = jsonObject.getString("twitter");
        website = jsonObject.getString("url");
//        imageUrl =

        return this;

    }

}
