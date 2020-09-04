package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class Wpis {

    ArrayList<String> atrybuty = new ArrayList<>();
    String dane_wejsciowe;
    String decyzja;

    public Wpis(String dane_wejsciowe) {
        this.dane_wejsciowe = dane_wejsciowe;

        String[] dane = dane_wejsciowe.split(",");
        for (int i = 0; i < dane.length-1; i++) {
            dodaj_atrybut(dane[i]);
        }
        decyzja = dane[dane.length-1];
    }

    void dodaj_atrybut(String wartosc){
        atrybuty.add(wartosc);
    }

}
