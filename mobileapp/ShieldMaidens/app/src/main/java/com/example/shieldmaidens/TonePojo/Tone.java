package com.example.shieldmaidens.TonePojo;

import com.google.gson.annotations.SerializedName;

public class Tone {
    @SerializedName("score")
    private Double score;
    @SerializedName("tone_id")
    private String toneId;
    @SerializedName("tone_name")
    private String toneName;

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getToneId() {
        return toneId;
    }

    public void setToneId(String toneId) {
        this.toneId = toneId;
    }

    public String getToneName() {
        return toneName;
    }

    public void setToneName(String toneName) {
        this.toneName = toneName;
    }


}
