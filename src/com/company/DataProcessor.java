package com.company;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataProcessor {

    File file;
    Scanner scanner;

    public DataProcessor(File file) {
        this.file = file;

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    ArrayList<Wpis> przetworz_dane(){

        ArrayList<Wpis> lista_wpisow = new ArrayList<>();
        while(scanner.hasNext()){
            lista_wpisow.add(new Wpis(scanner.nextLine()));
        }

        return lista_wpisow;
    }

    ArrayList<Test> przetworz_dane_testowe(){

        ArrayList<Test> lista_wpisow = new ArrayList<>();
        while(scanner.hasNext()){
            lista_wpisow.add(new Test(scanner.nextLine()));
        }

        return lista_wpisow;
    }




}
