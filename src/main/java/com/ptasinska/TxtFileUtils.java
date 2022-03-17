package com.ptasinska;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class TxtFileUtils {
    public static void insertValue(String value){
        int length = value.length();
        System.out.print(" " + value);
        for(int i=0; i<30-length; i++) System.out.print(" ");
        System.out.print("|");
    }

    public static void prepareFile(String filename) {
        int[] nByManufacturer = new int[7];
        String[] headers = {"Lp.", "nazwa producenta", "przekątna ekranu", "rozdzielczość ekranu",
                            "rodzaj powierzchni ekranu", "czy ekran jest dotykowy", "nazwa procesora",
                            "liczba rdzeni fizycznych", "prędkość taktowania MHz", "wielkość pamięci RAM",
                            "pojemność dysku", "rodzaj dysku", "nazwa układu graficznego", "pamięć układu graficznego",
                            "nazwa systemu operacyjnego", "rodzaj napędu fizycznego"};
        InputStreamReader isr = null;

        try{
            isr = new InputStreamReader(Objects.requireNonNull(TxtFileUtils.class.getResourceAsStream("/" + filename)));
        }
        catch(NullPointerException e){
            System.err.println("Nie znaleziono pliku o podanej nazwie!");
        }
        readFromFile(isr, headers, nByManufacturer);

    }

    public static void readFromFile(InputStreamReader isr, String[] headers, int[] nByManufacturer) {
        BufferedReader br = new BufferedReader(isr);
        try{
            for(int i=0;i<485;i++) System.out.print("*");
            System.out.print("\n");

            for(int i=0; i<headers.length; i++){
                if(i==0) System.out.print(headers[i] + "\t|");
                else insertValue(headers[i]);
            }
            System.out.print("\n");

            for(int i=0;i<485;i++) System.out.print("*");
            System.out.print("\n");

            String line = "";
            int j=1; //rows
            while((line = br.readLine()) != null){
                String[] values = line.split(";", -1);
                System.out.print(j+"\t|");

                int i=0; //cols
                for(String value: values){
                    if(value.isEmpty() && i < values.length - 1) insertValue("Brak informacji");
                    else if(!value.isEmpty()) insertValue(value);

                    if(i==0){
                        switch (value){
                            case "Dell": nByManufacturer[0]++; break;
                            case "Asus": nByManufacturer[1]++; break;
                            case "Fujitsu": nByManufacturer[2]++; break;
                            case "Huawei": nByManufacturer[3]++; break;
                            case "MSI": nByManufacturer[4]++; break;
                            case "Sony": nByManufacturer[5]++; break;
                            case "Samsung": nByManufacturer[6]++; break;
                        }
                    }
                    i++;
                    if(i> values.length-1){
                        System.out.println();
                        i=0;
                    }
                }
                j++;
            }
        }
        catch(IOException e){
            System.err.println("Błąd odczytu pliku!");
        }

        try {
            isr.close();
            br.close();
        } catch (IOException e) {
            System.err.println("Błąd przy zamykaniu pliku!");
        }

        System.out.println("\nLiczba komputerów poszczególnych marek: ");
        System.out.println("Dell: "+nByManufacturer[0]);
        System.out.println("Asus: "+nByManufacturer[1]);
        System.out.println("Fujitsu: "+nByManufacturer[2]);
        System.out.println("Huawei: "+nByManufacturer[3]);
        System.out.println("MSI: "+nByManufacturer[4]);
        System.out.println("Sony: "+nByManufacturer[5]);
        System.out.println("Samsung: "+nByManufacturer[6]);
    }

}
