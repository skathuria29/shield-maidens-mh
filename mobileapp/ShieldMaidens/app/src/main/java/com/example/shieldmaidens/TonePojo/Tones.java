package com.example.shieldmaidens.TonePojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Tones {
        @SerializedName("document_tone")
        private DocumentTone documentTone;
        @SerializedName("sentences_tone")
        private List<SentencesTone> sentencesTone = null;

        public DocumentTone getDocumentTone() {
            return documentTone;
        }

        public void setDocumentTone(DocumentTone documentTone) {
            this.documentTone = documentTone;
        }

        public List<SentencesTone> getSentencesTone() {
            return sentencesTone;
        }

        public void setSentencesTone(List<SentencesTone> sentencesTone) {
            this.sentencesTone = sentencesTone;
        }

}
