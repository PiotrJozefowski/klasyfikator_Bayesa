package com.company;

import com.sun.security.jgss.GSSUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        boolean zgodnosc = true;

        Scanner odczyt = new Scanner(System.in);
        String komenda = odczyt.nextLine();




            DataProcessor dp = new DataProcessor(new File(("trainingset.csv")));
            ArrayList<Wpis> lista_wpisow = dp.przetworz_dane();

            DataProcessor dp_testow = new DataProcessor(new File("testset.csv"));
            ArrayList<Test> lista_testow = dp_testow.przetworz_dane_testowe();

            Licznik_wariacji lw = new Licznik_wariacji(lista_wpisow);
            lw.uzupelnij();                                                                             // zliczenie wariacji

            ArrayList<ArrayList<String>> lista_wariacji_atrybutow = lw.lista_wariacji_atrybutow;



            HashMap<String, ArrayList<Wpis>> mapa_segregowana_decyzjami = new HashMap<>();

            lista_wpisow.forEach((wpis -> {
                if (mapa_segregowana_decyzjami.containsKey(wpis.decyzja)) {
                    mapa_segregowana_decyzjami.get(wpis.decyzja).add(wpis);
                } else {
                    mapa_segregowana_decyzjami.put(wpis.decyzja, new ArrayList<>());
                    mapa_segregowana_decyzjami.get(wpis.decyzja).add(wpis);
                }
            }));


//            mapa_segregowana_decyzjami.forEach((klucz, lista) -> {
//                System.out.println("KLUCZ : " + klucz);
//                lista.forEach((wpis -> {
//                    wpis.atrybuty.forEach((atrybut) -> {
//                        System.out.print(atrybut + " ");
//                    });
//                    System.out.println();
//                }));
//            });

        if(komenda.equals("plik")) {

            for (int i = 0; i < lista_testow.size(); i++) {

                Test test = lista_testow.get(i);

                double[][] wynik = new double[test.atrybuty.size()][2];

                ArrayList<String> lista_kluczy = new ArrayList<>();
                ArrayList<Double> lista_wynikow = new ArrayList<>();


                mapa_segregowana_decyzjami.forEach((klucz, lista) -> {

                    for (int j = 0; j < wynik[0].length; j++) {
                        wynik[0][j] = 1;                                                        // wygladzenie plus nadanie wartosci poczatkowych
                        wynik[1][j] = lista_wariacji_atrybutow.get(j).size();
                    }

                    double ilosc_w_decyzji = lista.size();
                    double ilosc_wszystkich = lista_wpisow.size();


                    for (int j = 0; j < lista.size(); j++) {
                        Wpis wpis = lista.get(j);
                        for (int k = 0; k < wpis.atrybuty.size(); k++) {
                            if (test.atrybuty.get(k).equals(wpis.atrybuty.get(k))) {
                                wynik[k][0] = wynik[k][0] + 1;
                                wynik[k][1] = wynik[k][1] + ilosc_w_decyzji;
                            }
                        }
                    }

                    double wynik_ostateczny = (double) (ilosc_w_decyzji / ilosc_wszystkich);

                    for (int j = 0; j < wynik[0].length; j++) {
                        wynik_ostateczny = (double) ((wynik_ostateczny * wynik[0][j]) / wynik[1][j]);
                    }


                    lista_kluczy.add(klucz);
                    lista_wynikow.add(wynik_ostateczny);


                });

                int index = 0;
                double max = 0;

                for (int j = 0; j < lista_wynikow.size(); j++) {
                    double a = lista_wynikow.get(j);
                    //System.out.println("a: " + a + " " + "    MAX: " + max);
                    if (a > max) {                                                                        ///// ............. !>?!?!??!?!?!!?!?!?
                      //  System.out.println("NOWY MAX = " + lista_wynikow.get(j));
                        max = lista_wynikow.get(j);
                        index = j;
                    }
                }


            System.out.println("DECYZJA " + lista_kluczy.get(index));
            System.out.println(lista_kluczy.get(0) + " " + lista_wynikow.get(0));
            System.out.println(lista_kluczy.get(1) + " " + lista_wynikow.get(1));
            System.out.println("WYNIKIEM " + max);
                System.out.println();

            }

        }

        if(komenda.equals("wprowadz")){

            String dane = odczyt.nextLine();
            Test test = new Test(dane);

            HashMap<String, Double> mapa_wartosci_wyjsciowych = new HashMap<>();
            double[][] wynik = new double[test.atrybuty.size()][2];

            ArrayList<String> lista_kluczy = new ArrayList<>();
            ArrayList<Double> lista_wynikow = new ArrayList<>();


            mapa_segregowana_decyzjami.forEach((klucz, lista) -> {

                for (int j = 0; j < wynik[0].length; j++) {
                    wynik[0][j] = 1;                                                        // wygladzenie plus nadanie wartosci poczatkowych
                    wynik[1][j] = lista_wariacji_atrybutow.get(j).size();
                }

                double ilosc_w_decyzji = lista.size();
                double ilosc_wszystkich = lista_wpisow.size();


                for (int j = 0; j < lista.size(); j++) {
                    Wpis wpis = lista.get(j);
                    for (int k = 0; k < wpis.atrybuty.size(); k++) {
                        if (test.atrybuty.get(k).equals(wpis.atrybuty.get(k))) {
                            wynik[k][0] = wynik[k][0] + 1;
                            wynik[k][1] = wynik[k][1] + ilosc_w_decyzji;
                        }
                    }
                }

                double wynik_ostateczny = (double) (ilosc_w_decyzji / ilosc_wszystkich);

                for (int j = 0; j < wynik[0].length; j++) {
                    wynik_ostateczny = (double) ((wynik_ostateczny * wynik[0][j]) / wynik[1][j]);
                }


                lista_kluczy.add(klucz);
                lista_wynikow.add(wynik_ostateczny);


            });

            int index = 0;
            double max = 0;

            for (int j = 0; j < lista_wynikow.size(); j++) {
                double a = lista_wynikow.get(j);
               // System.out.println("a: " + a + " " + "    MAX: " + max);
                if (a > max) {                                                                        ///// ............. !>?!?!??!?!?!!?!?!?
                 //   System.out.println("NOWY MAX = " + lista_wynikow.get(j));
                    max = lista_wynikow.get(j);
                    index = j;
                }
            }

            System.out.println("DECYZJA " + lista_kluczy.get(index));
            System.out.println(lista_kluczy.get(0) + " " + lista_wynikow.get(0));
            System.out.println(lista_kluczy.get(1) + " " + lista_wynikow.get(1));
            System.out.println("WYNIKIEM " + max);
            System.out.println();

        }







    }
}
