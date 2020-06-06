package com.example.shieldmaidens;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {
    @SerializedName("alternatives")
    private List<Alternative> alternatives = null;
    @SerializedName("final")
    private Boolean _final;

    public List<Alternative> getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(List<Alternative> alternatives) {
        this.alternatives = alternatives;
    }

    public Boolean getFinal() {
        return _final;
    }

    public void setFinal(Boolean _final) {
        this._final = _final;
    }
}
