package com.ptasinska;

import com.ptasinska.data.Laptop;
import com.ptasinska.data.Laptops;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

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

    public List<Laptop> readFromXml(File file){
        List<Laptop> result = new ArrayList<>();
        try {
            JAXBContext context = JAXBContext.newInstance(Laptops.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            Laptops items = (Laptops) unmarshaller.unmarshal(file);
            for(Laptop item : items.getLaptops()){
                result.add(item);
            }

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return result;
    }
    public boolean saveToXml(List<Laptop> items, File file){
        try {
            Laptops laptops = new Laptops();
            LocalDateTime dateTime = LocalDateTime.now(ZoneId.of("Europe/Warsaw"));
            String dateTimePattern = "yyyy-MM-dd"+" 'T' "+"HH:mm";
            String moddate = dateTime.format(DateTimeFormatter.ofPattern(dateTimePattern));
            laptops.setModdate(moddate);
            laptops.setLaptops(items);

            JAXBContext context = JAXBContext.newInstance(Laptops.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(laptops, file);

        } catch (JAXBException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
