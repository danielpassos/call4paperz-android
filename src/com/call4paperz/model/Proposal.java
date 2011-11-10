package com.call4paperz.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Proposal {

    private Integer id;
    private String name;
    private String description;
    private Integer points;
    private String speaker ;

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

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    @Override
    public String toString() {
        return name;
    }

    public Proposal fromJSON(JSONObject jsonObject) throws JSONException {
        id = jsonObject.getInt("id");
        name = jsonObject.getString("name");
        description = jsonObject.getString("description");
        points = jsonObject.getInt("acceptance_points");
        speaker = jsonObject.getJSONObject("user").getString("name");

        return this;
    }

}