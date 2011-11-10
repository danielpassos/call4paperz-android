package com.call4paperz.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event implements Serializable {

    private Integer id;
    private String name;
    private String description;
    private Date date;
    private String twitter;
    private String website;
    private String imageUrl;
    private String organizer;

    private Integer proposals;
    private Integer votes;
    private Integer comments;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getStringDate() {
        return new SimpleDateFormat("MMMMM dd, yyyy").format(date);
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

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public Integer getProposals() {
        return proposals;
    }

    public void setProposals(Integer proposals) {
        this.proposals = proposals;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return name;
    }

    public Event fromJSON(JSONObject jsonObject) throws JSONException {

        id = jsonObject.getInt("id");
        name = jsonObject.getString("name");
        description = jsonObject.getString("description");
        twitter = jsonObject.getString("twitter");
        website = jsonObject.getString("url");
        imageUrl = jsonObject.getJSONObject("picture").getJSONObject("cropped").getString("url");

        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(jsonObject.getString("occurs_at"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        organizer = jsonObject.getJSONObject("user").getString("name");

        proposals = jsonObject.getInt("proposals_count");
        votes = jsonObject.getInt("votes_count");
        comments = jsonObject.getInt("comments_count");

        return this;

    }



}
