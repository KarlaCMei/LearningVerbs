package com.example.learningverbs.model;


import java.io.Serializable;

public class Verb implements Serializable {

    private String verbId;
    private Boolean regular;
    private String verbSpanishPresent;
    private String verbSpanishPast;
    private String verbSpanishFuture;
    private String verbEnglishPresent;
    private String verbEnglishPast;
    private String verbEnglishFuture;
    private ExampleVerb exampleVerb;

    public ExampleVerb getExampleVerb() {
        return exampleVerb;
    }

    public void setExampleVerb(ExampleVerb exampleVerb) {
        this.exampleVerb = exampleVerb;
    }

    public String getVerbId() {
        return verbId;
    }

    public void setVerbId(String verbId) {
        this.verbId = verbId;
    }

    public Boolean getRegular() {
        return regular;
    }

    public void setRegular(Boolean regular) {
        this.regular = regular;
    }

    public String getVerbSpanishPresent() {
        return verbSpanishPresent;
    }

    public void setVerbSpanishPresent(String verbSpanishPresent) {
        this.verbSpanishPresent = verbSpanishPresent;
    }

    public String getVerbSpanishPast() {
        return verbSpanishPast;
    }

    public void setVerbSpanishPast(String verbSpanishPast) {
        this.verbSpanishPast = verbSpanishPast;
    }

    public String getVerbSpanishFuture() {
        return verbSpanishFuture;
    }

    public void setVerbSpanishFuture(String verbSpanishFuture) {
        this.verbSpanishFuture = verbSpanishFuture;
    }

    public String getVerbEnglishPresent() {
        return verbEnglishPresent;
    }

    public void setVerbEnglishPresent(String verbEnglishPresent) {
        this.verbEnglishPresent = verbEnglishPresent;
    }

    public String getVerbEnglishPast() {
        return verbEnglishPast;
    }

    public void setVerbEnglishPast(String verbEnglishPast) {
        this.verbEnglishPast = verbEnglishPast;
    }

    public String getVerbEnglishFuture() {
        return verbEnglishFuture;
    }

    public void setVerbEnglishFuture(String verbEnglishFuture) {
        this.verbEnglishFuture = verbEnglishFuture;
    }

    @Override
    public String toString() {
        return "Verb{" +
                "isRegular=" + regular +
                ", verbSpanish='"  + '\'' +
                ", verbEnglish='"  + '\'' +
                '}';
    }
}
