package com.example.learningverbs.model;


import java.io.Serializable;

public class Verb implements Serializable {
    private String image;
    private String verbId;
    private Boolean regular;
    private String verbSpanishPresent;
    private String verbEnglishPresent;
   private ExampleVerb exampleVerbPresent;
   private ExampleVerb exampleVerbPast;
   private ExampleVerb exampleVerbFuture;

    public Verb(){}

    public Verb(String image, String verbId, Boolean regular, String verbSpanishPresent, String verbEnglishPresent, ExampleVerb exampleVerbPresent, ExampleVerb exampleVerbPast, ExampleVerb exampleVerbFuture) {
        this.image = image;
        this.verbId = verbId;
        this.regular = regular;
        this.verbSpanishPresent = verbSpanishPresent;
        this.verbEnglishPresent = verbEnglishPresent;
        this.exampleVerbPresent = exampleVerbPresent;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getVerbEnglishPresent() {
        return verbEnglishPresent;
    }

    public void setVerbEnglishPresent(String verbEnglishPresent) {
        this.verbEnglishPresent = verbEnglishPresent;
    }

    public ExampleVerb getExampleVerbPresent() {
        return exampleVerbPresent;
    }

    public void setExampleVerbPresent(ExampleVerb exampleVerbPresent) {
        this.exampleVerbPresent = exampleVerbPresent;
    }

    public ExampleVerb getExampleVerbPast() {
        return exampleVerbPast;
    }

    public void setExampleVerbPast(ExampleVerb exampleVerbPast) {
        this.exampleVerbPast = exampleVerbPast;
    }

    public ExampleVerb getExampleVerbFuture() {
        return exampleVerbFuture;
    }

    public void setExampleVerbFuture(ExampleVerb exampleVerbFuture) {
        this.exampleVerbFuture = exampleVerbFuture;
    }

    @Override
    public String toString() {
        return "Verb{" +
                "isRegular=" + regular +
                ", verbSpanish='" + verbSpanishPresent + '\'' +
                ", verbEnglish='" + verbEnglishPresent + '\'' +
                '}';
    }

}
