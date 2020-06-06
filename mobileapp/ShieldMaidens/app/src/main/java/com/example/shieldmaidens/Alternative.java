package com.example.shieldmaidens;

import com.google.gson.annotations.SerializedName;

public class Alternative {
    @SerializedName("confidence")
    private Double confidence;
    @SerializedName("transcript")
    private String transcript;

    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    public String getTranscript() {
        return transcript;
    }

    public void setTranscript(String transcript) {
        this.transcript = transcript;
    }

}
