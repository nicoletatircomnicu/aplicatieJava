//package com.company;

import java.util.Objects;

/**
 * clasa reprezinta ierarhia locatiei (tara/judet/oras)
 * aici se pot adauga campuri precum localitate, sat, etc.
 */
public class Ierarhie {
    String tara;
    String judet;
    String oras;
    // aici se pot adauga campuri


    public Ierarhie(String tara, String judet, String oras) {
        this.tara = tara;
        this.judet = judet;
        this.oras = oras;
    }

    @Override
    public String toString() {
        return "Ierarhie{" +
                "tara='" + tara + '\'' +
                ", judet='" + judet + '\'' +
                ", oras='" + oras + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ierarhie ierarhie = (Ierarhie) o;
        return Objects.equals(tara, ierarhie.tara) &&
                Objects.equals(judet, ierarhie.judet) &&
                Objects.equals(oras, ierarhie.oras);
    }

}
