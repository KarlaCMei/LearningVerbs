package com.example.learningverbs.model;

import java.io.Serializable;

public class ExampleVerb implements Serializable {
    private String verbSpanish;
    private String verbEnglish;
    private String phraseAffirmativeSpanish;
    private String phraseNegativeSpanish;
    private String phraseQuestionSpanish;
    private String phraseAffirmativeEnglish;
    private String phraseNegativeEnglish;
    private String phraseQuestionEnglish;

    public ExampleVerb() {

    }

    public ExampleVerb(String verbSpanish, String verbEnglish, String phraseAffirmativeSpanish, String phraseNegativeSpanish, String phraseQuestionSpanish, String phraseAffirmativeEnglish, String phraseNegativeEnglish, String phraseQuestionEnglish) {
        this.verbSpanish = verbSpanish;
        this.verbEnglish = verbEnglish;
        this.phraseAffirmativeSpanish = phraseAffirmativeSpanish;
        this.phraseNegativeSpanish = phraseNegativeSpanish;
        this.phraseQuestionSpanish = phraseQuestionSpanish;
        this.phraseAffirmativeEnglish = phraseAffirmativeEnglish;
        this.phraseNegativeEnglish = phraseNegativeEnglish;
        this.phraseQuestionEnglish = phraseQuestionEnglish;
    }

    public String getVerbSpanish() {
        return verbSpanish;
    }

    public void setVerbSpanish(String verbSpanish) {
        this.verbSpanish = verbSpanish;
    }

    public String getVerbEnglish() {
        return verbEnglish;
    }

    public void setVerbEnglish(String verbEnglish) {
        this.verbEnglish = verbEnglish;
    }

    public String getPhraseAffirmativeSpanish() {
        return phraseAffirmativeSpanish;
    }

    public void setPhraseAffirmativeSpanish(String phraseAffirmativeSpanish) {
        this.phraseAffirmativeSpanish = phraseAffirmativeSpanish;
    }

    public String getPhraseNegativeSpanish() {
        return phraseNegativeSpanish;
    }

    public void setPhraseNegativeSpanish(String phraseNegativeSpanish) {
        this.phraseNegativeSpanish = phraseNegativeSpanish;
    }

    public String getPhraseQuestionSpanish() {
        return phraseQuestionSpanish;
    }

    public void setPhraseQuestionSpanish(String phraseQuestionSpanish) {
        this.phraseQuestionSpanish = phraseQuestionSpanish;
    }

    public String getPhraseAffirmativeEnglish() {
        return phraseAffirmativeEnglish;
    }

    public void setPhraseAffirmativeEnglish(String phraseAffirmativeEnglish) {
        this.phraseAffirmativeEnglish = phraseAffirmativeEnglish;
    }

    public String getPhraseNegativeEnglish() {
        return phraseNegativeEnglish;
    }

    public void setPhraseNegativeEnglish(String phraseNegativeEnglish) {
        this.phraseNegativeEnglish = phraseNegativeEnglish;
    }

    public String getPhraseQuestionEnglish() {
        return phraseQuestionEnglish;
    }

    public void setPhraseQuestionEnglish(String phraseQuestionEnglish) {
        this.phraseQuestionEnglish = phraseQuestionEnglish;
    }
}
