package com.example.shieldmaidens;

import com.google.gson.annotations.SerializedName;

import java.util.List;

class SpeechText {
    @SerializedName("results")
    private List<Result> results = null;
    @SerializedName("result_index")
    private Integer resultIndex;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Integer getResultIndex() {
        return resultIndex;
    }

    public void setResultIndex(Integer resultIndex) {
        this.resultIndex = resultIndex;
    }

}
