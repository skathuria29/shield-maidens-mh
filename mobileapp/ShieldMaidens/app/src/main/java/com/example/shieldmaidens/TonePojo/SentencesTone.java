package com.example.shieldmaidens.TonePojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SentencesTone {


        @SerializedName("sentence_id")
        private Integer sentenceId;
        @SerializedName("text")
        private String text;
        @SerializedName("tones")
        private List<Tone_> tones = null;

        public Integer getSentenceId() {
            return sentenceId;
        }

        public void setSentenceId(Integer sentenceId) {
            this.sentenceId = sentenceId;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public List<Tone_> getTones() {
            return tones;
        }

        public void setTones(List<Tone_> tones) {
            this.tones = tones;
        }

}
