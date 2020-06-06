package com.example.shieldmaidens;

import com.google.gson.annotations.SerializedName;

public class Session {

    @SerializedName("name")
    private String name;
    @SerializedName("professional")
    private String professional;
    @SerializedName("type")
    private String type;
    @SerializedName("count")
    private Integer count;
    @SerializedName("date")
    private String date;
    @SerializedName("createdAt")
    private long createdAt;
    @SerializedName("modifiedAt")
    private long modifiedAt;
    @SerializedName("_id")
    private String id;
    @SerializedName("sessionId")
    private String sessionId;
    @SerializedName("__v")
    private Integer v;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Integer modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}