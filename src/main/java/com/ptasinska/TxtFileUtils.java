package com.ptasinska;

import com.ptasinska.data.Laptop;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TxtFileUtils {

    public static final String[] HEADERS = {"Lp.", "nazwa producenta", "przekątna ekranu", "rozdzielczość ekranu",
            "rodzaj powierzchni ekranu", "czy ekran jest dotykowy", "nazwa procesora",
            "liczba rdzeni fizycznych", "prędkość taktowania MHz", "wielkość pamięci RAM",
            "pojemność dysku", "rodzaj dysku", "nazwa układu graficznego", "pamięć układu graficznego",
            "nazwa systemu operacyjnego", "rodzaj napędu fizycznego"};


    public static List<Laptop> readFromFile(String filePath) {
        FileReader fr = null;
        BufferedReader br = null;
        List<Laptop> laptops = new ArrayList<>();
        String line = "";
        try{
            fr = new FileReader(filePath);
            br = new BufferedReader(fr);
            int i=0;
            while((line = br.readLine())!=null){
                String[] values = line.split(";",-1);
                Laptop laptop = new Laptop(i+1, values);
                laptops.add(laptop);
                i++;
            }
        }
        catch(FileNotFoundException e){
            System.err.println("Nie znaleziono pliku!");
        }
        catch (IOException e){
            System.err.println("Błąd odczytu pliku!");
        }
        try {
            fr.close();
            br.close();
        } catch (IOException e) {
            System.err.println("Błąd przy zamykaniu pliku!");
        }

        return laptops;
    }

    public static boolean saveToFile(String filePath, List<Laptop> laptops) {
        PrintWriter printWriter = null;
        try{
            printWriter = new PrintWriter(filePath);
            for(int i=0; i<laptops.size()-1; i++){
                printWriter.println(laptops.get(i).toString());
            }
            printWriter.print(laptops.get(laptops.size()-1).toString());
            printWriter.close();
            return true;
        }
        catch (FileNotFoundException e){
            System.out.println("Nie znaleziono pliku!");
            return false;
        }
    }
}
