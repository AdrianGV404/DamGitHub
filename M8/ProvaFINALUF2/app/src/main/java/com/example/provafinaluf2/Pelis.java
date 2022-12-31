package com.example.provafinaluf2;

public class Pelis {
    private String titol;
    private String genere;
    private String durada;
    private String nota;

        public Pelis() {
    }

    public Pelis(String titol, String genere, String durada, String nota) {
        this.titol = titol;
        this.genere = genere;
        this.durada = durada;
        this.nota = nota;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public String getDurada() {
        return durada;
    }

    public void setDurada(String durada) {
        this.durada = durada;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Pelis{" +
                "titol='" + titol + '\'' +
                ", genere='" + genere + '\'' +
                ", durada='" + durada + '\'' +
                ", nota='" + nota + '\'' +
                '}';
    }
}
