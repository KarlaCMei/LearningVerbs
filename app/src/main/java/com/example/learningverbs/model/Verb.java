package com.example.learningverbs.model;

public class Verb {
    private Boolean esRegular;
    private String verboEspañol;
    private String verboEspañolFuturo;
    private String verboEspañolPasado;
    private String verboEspañolPresente;
    private String verboIngles;
    private String verboInglesFuturo;
    private String verboInglesPasado;
    private String verboInglesPresente;

    public Verb(){}

    public Verb(Boolean esRegular, String verboEspañol, String verboEspañolFuturo, String verboEspañolPasado, String verboEspañolPresente, String verboIngles, String verboInglesFuturo, String verboInglesPasado, String verboInglesPresente) {
        this.esRegular = esRegular;
        this.verboEspañol = verboEspañol;
        this.verboEspañolFuturo = verboEspañolFuturo;
        this.verboEspañolPasado = verboEspañolPasado;
        this.verboEspañolPresente = verboEspañolPresente;
        this.verboIngles = verboIngles;
        this.verboInglesFuturo = verboInglesFuturo;
        this.verboInglesPasado = verboInglesPasado;
        this.verboInglesPresente = verboInglesPresente;
    }

    public boolean getEsRegular() {
        return esRegular;
    }

    public void setEsRegular(Boolean esRegular) {
        this.esRegular = esRegular;
    }

    public String getVerboEspañol() {
        return verboEspañol;
    }

    public void setVerboEspañol(String verboEspañol) {
        this.verboEspañol = verboEspañol;
    }

    public String getVerboEspañolFuturo() {
        return verboEspañolFuturo;
    }

    public void setVerboEspañolFuturo(String verboEspañolFuturo) {
        this.verboEspañolFuturo = verboEspañolFuturo;
    }

    public String getVerboEspañolPasado() {
        return verboEspañolPasado;
    }

    public void setVerboEspañolPasado(String verboEspañolPasado) {
        this.verboEspañolPasado = verboEspañolPasado;
    }

    public String getVerboEspañolPresente() {
        return verboEspañolPresente;
    }

    public void setVerboEspañolPresente(String verboEspañolPresente) {
        this.verboEspañolPresente = verboEspañolPresente;
    }

    public String getVerboIngles() {
        return verboIngles;
    }

    public void setVerboIngles(String verboIngles) {
        this.verboIngles = verboIngles;
    }

    public String getVerboInglesFuturo() {
        return verboInglesFuturo;
    }

    public void setVerboInglesFuturo(String verboInglesFuturo) {
        this.verboInglesFuturo = verboInglesFuturo;
    }

    public String getVerboInglesPasado() {
        return verboInglesPasado;
    }

    public void setVerboInglesPasado(String verboInglesPasado) {
        this.verboInglesPasado = verboInglesPasado;
    }

    public String getVerboInglesPresente() {
        return verboInglesPresente;
    }

    public void setVerboInglesPresente(String verboInglesPresente) {
        this.verboInglesPresente = verboInglesPresente;
    }

    @Override
    public String toString() {
        return "Verb{" +
                "esRegular=" + esRegular +
                ", verboEspañol='" + verboEspañol + '\'' +
                ", verboEspañolFuturo='" + verboEspañolFuturo + '\'' +
                ", verboEspañolPasado='" + verboEspañolPasado + '\'' +
                ", verboEspañolPresente='" + verboEspañolPresente + '\'' +
                ", verboIngles='" + verboIngles + '\'' +
                ", verboInglesFuturo='" + verboInglesFuturo + '\'' +
                ", verboInglesPasado='" + verboInglesPasado + '\'' +
                ", verboInglesPresente='" + verboInglesPresente + '\'' +
                '}';
    }
}
