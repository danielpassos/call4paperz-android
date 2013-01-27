package com.call4paperz.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Proposal implements Serializable {

    private Integer id;
    private String name;
    private String description;
    private Integer acceptancePoints;
    private Integer votesCount;
    private User user;

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

    public Integer getAcceptancePoints() {
        return acceptancePoints;
    }

    public void setAcceptancePoints(Integer acceptancePoints) {
        this.acceptancePoints = acceptancePoints;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getVotesCount() {
        return votesCount;
    }

    public void setVotesCount(Integer votesCount) {
        this.votesCount = votesCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return name;
    }

}
