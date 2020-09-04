package com.company;

import java.util.ArrayList;

public class Licznik_wariacji {

    ArrayList<Wpis> lista_wpisow;
    ArrayList<ArrayList<String>> lista_wariacji_atrybutow = new ArrayList<>();

    public Licznik_wariacji(ArrayList<Wpis> lista_wpisow) {
        this.lista_wpisow = lista_wpisow;
    }

    public void uzupelnij(){

        for (int i = 0; i < lista_wpisow.get(0).atrybuty.size(); i++) {
            lista_wariacji_atrybutow.add(new ArrayList<>());                                                        // dodanie list wartosci dla atrybutow
        }

        for (int i = 0; i < lista_wpisow.get(0).atrybuty.size(); i++) {
            for (int j = 0; j < lista_wpisow.size(); j++) {
                if(!lista_wariacji_atrybutow.get(i).contains(lista_wpisow.get(j).atrybuty.get(i))) {                // zliczenie wariacji w kazdym atrybucie
                    lista_wariacji_atrybutow.get(i).add(lista_wpisow.get(j).atrybuty.get(i));
                }
            }
        }


    }


}
