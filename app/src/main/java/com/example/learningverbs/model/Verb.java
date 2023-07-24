package com.example.learningverbs.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Verb implements Serializable {
    private Boolean regular;
    private String verbSpanish;
    private String verbEnglish;

    private ArrayList<ExampleVerbPresent> exampleVerbPresent;

    public ArrayList<ExampleVerbPresent> getExampleVerbPresent() {
        return exampleVerbPresent;
    }

    public void setExampleVerbPresent(ArrayList<ExampleVerbPresent> exampleVerbPresent) {
        this.exampleVerbPresent = exampleVerbPresent;
    }
/*private String verboEspañolFuturo;
    private String verboEspañolPasado;
    private String verboEspañolPresente;
    private String verboInglesFuturo;
    private String verboInglesPasado;
    private String verboInglesPresente;*/

    public Verb(){}

    public Verb(Boolean regular, String verbSpanish, String verbEnglish) {
        this.regular = regular;
        this.verbSpanish = verbSpanish;
        this.verbEnglish = verbEnglish;
    }

    public Boolean getRegular() {
        return regular;
    }

    public void setRegular(Boolean regular) {
        this.regular = regular;
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

    @Override
    public String toString() {
        return "Verb{" +
                "isRegular=" + regular +
                ", verbSpanish='" + verbSpanish + '\'' +
                ", verbEnglish='" + verbEnglish + '\'' +
                '}';
    }
}
