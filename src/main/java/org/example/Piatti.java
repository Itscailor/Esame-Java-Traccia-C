package org.example;

public class Piatti {
    String descrizione;
    int id;
    String nome;
    double Prezzo;
    public Piatti(String descrizione, int id, String name, Double price) {
        this.id = id;
        this.nome = name;
        this.Prezzo = price;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPrezzo() {
        return Prezzo;
    }

}