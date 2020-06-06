package com.example.shieldmaidens.TonePojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DocumentTone {
        @SerializedName("tones")
        private List<Tone> tones = null;

        public List<Tone> getTones() {
            return tones;
        }

        public void setTones(List<Tone> tones) {
            this.tones = tones;
        }
}
