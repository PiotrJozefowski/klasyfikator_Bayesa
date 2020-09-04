package com.company;

import java.util.ArrayList;

public class Test {

    ArrayList<String> atrybuty = new ArrayList<>();
    String dane_wejsciowe;
    String decyzja;

    public Test(String dane_wejsciowe) {
        this.dane_wejsciowe = dane_wejsciowe;

        String[] dane = dane_wejsciowe.split(",");
        for (int i = 0; i < dane.length; i++) {
            dodaj_atrybut(dane[i]);
        }
    }

    void dodaj_atrybut(String wartosc){
        atrybuty.add(wartosc);
    }




}
