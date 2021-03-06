package com.ptasinska.data;

import jakarta.xml.bind.annotation.*;
import javafx.beans.property.SimpleBooleanProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@XmlRootElement(name = "laptop")
@XmlAccessorType(XmlAccessType.FIELD)
public class Laptop {
    @XmlAttribute
    private int id;
    @XmlElement
    private String manufacturer;
    @XmlElement
    private Screen screen;
    @XmlElement
    private Processor processor;
    @XmlElement(name="ram")
    private String ramSize;
    @XmlElement
    private Disc disc;
    @XmlElement(name="graphic_card")
    private GraphicCard graphicCard;
    @XmlElement
    private String os;
    @XmlElement(name="disc_reader")
    private String driveType;

    private SimpleBooleanProperty modified = new SimpleBooleanProperty(false);
    private SimpleBooleanProperty duplicated = new SimpleBooleanProperty(false);

    public boolean isModified() {
        return modified.get();
    }

    public SimpleBooleanProperty modifiedProperty() {
        return modified;
    }

    public void setModified(boolean modified) {
        this.modified.set(modified);
    }

    public boolean isDuplicated() {
        return duplicated.get();
    }

    public SimpleBooleanProperty duplicatedProperty() {
        return duplicated;
    }

    public void setDuplicated(boolean duplicated) {
        this.duplicated.set(duplicated);
    }

    public Laptop(){
        String[] data = new String[15];
        for(int i=0;i<data.length;i++){
            data[i] = "Brak informacji";
        }
        this.id = id;
        this.manufacturer = data[0];
        this.screen = Screen.builder().size(data[1]).resolution(data[2]).type(data[3]).touch(data[4]).build();
        if(!data[6].equals("Brak informacji"))
            this.processor = Processor.builder().name(data[5]).physicalCores(Integer.parseInt(data[6])).clockSpeed(data[7]).build();
        else this.processor = Processor.builder().name(data[5]).physicalCores(0).clockSpeed(data[7]).build();
        this.ramSize = data[8];
        this.disc = Disc.builder().storage(data[9]).type(data[10]).build();
        this.graphicCard = GraphicCard.builder().name(data[11]).memory(data[12]).build();
        this.os = data[13];
        this.driveType = data[14];

        this.modified.set(false);
        this.duplicated.set(false);
    }

    public Laptop(int id, String[] data) {
        for(int i=0;i<data.length;i++){
            if(data[i] == null) data[i] = "Brak informacji";
            else if(data[i].trim().isEmpty()) data[i]="Brak informacji";
        }
        this.id = id;
        this.manufacturer = data[0];
        this.screen = Screen.builder().size(data[1]).resolution(data[2]).type(data[3]).touch(data[4]).build();
        if(!data[6].equals("Brak informacji"))
            this.processor = Processor.builder().name(data[5]).physicalCores(Integer.parseInt(data[6])).clockSpeed(data[7]).build();
        else this.processor = Processor.builder().name(data[5]).physicalCores(0).clockSpeed(data[7]).build();
        this.ramSize = data[8];
        this.disc = Disc.builder().storage(data[9]).type(data[10]).build();
        this.graphicCard = GraphicCard.builder().name(data[11]).memory(data[12]).build();
        this.os = data[13];
        this.driveType = data[14];

        this.modified.set(false);
        this.duplicated.set(false);
    }

    //new row
    public Laptop(int id) {
        String[] data = new String[15];
        for(int i=0;i<data.length;i++){
            data[i]="Brak informacji";
        }
        this.id = id;
        this.manufacturer = data[0];
        this.screen = Screen.builder().size(data[1]).resolution(data[2]).type(data[3]).touch(data[4]).build();
        if(!data[6].equals("Brak informacji"))
        this.processor = Processor.builder().name(data[5]).physicalCores(Integer.parseInt(data[6])).clockSpeed(data[7]).build();
        else this.processor = Processor.builder().name(data[5]).physicalCores(0).clockSpeed(data[7]).build();
        this.ramSize = data[8];
        this.disc = Disc.builder().storage(data[9]).type(data[10]).build();
        this.graphicCard = GraphicCard.builder().name(data[11]).memory(data[12]).build();
        this.os = data[13];
        this.driveType = data[14];

        this.modified.set(false);
        this.duplicated.set(false);
    }

    public void setValueAt(int column, String value){
        if(value.trim().isEmpty()) value="Brak informacji";
        switch (column){
            case 1: this.manufacturer = value; break;
            case 2: this.screen.size = value; break;
            case 3: this.screen.resolution = value; break;
            case 4: this.screen.type = value; break;
            case 5: this.screen.touch = value; break;
            case 6: this.processor.name = value; break;
            case 7: if(!value.equals("Brak informacji")) this.processor.physicalCores = Integer.parseInt(value);
                else this.processor.physicalCores = 0; break;
            case 8: this.processor.clockSpeed = value; break;
            case 9: this.ramSize = value; break;
            case 10: this.disc.storage = value; break;
            case 11: this.disc.type = value; break;
            case 12: this.graphicCard.name = value; break;
            case 13: this.graphicCard.memory = value; break;
            case 14: this.os = value; break;
            case 15: this.driveType = value; break;
        }
    }

    @Override
    public String toString() {
        return manufacturer + ';' +
                screen.size + ';' +
                screen.resolution + ';' +
                screen.type + ';' +
                screen.touch + ';' +
                processor.name + ';' +
                processor.physicalCores + ';' +
                processor.clockSpeed + ';' +
                ramSize + ';' +
                disc.storage + ';' +
                disc.type + ';' +
                graphicCard.name + ';' +
                graphicCard.memory + ';' +
                os + ';' +
                driveType + ';';
    }

    public String sqlInsert() {
        return "INSERT INTO laptops VALUES (NULL, '" + manufacturer + "','" +
                screen.size + "','" +
                screen.resolution + "','" +
                screen.type + "','" +
                screen.touch + "','" +
                processor.name + "','" +
                processor.physicalCores + "','" +
                processor.clockSpeed + "','" +
                ramSize + "','" +
                disc.storage + "','" +
                disc.type + "','" +
                graphicCard.name + "','" +
                graphicCard.memory + "','" +
                os + "','" +
                driveType + "')";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Laptop laptop = (Laptop) o;

        if (!manufacturer.equals(laptop.manufacturer)) return false;
        if (!screen.equals(laptop.screen)) return false;
        if (!processor.equals(laptop.processor)) return false;
        if (!ramSize.equals(laptop.ramSize)) return false;
        if (!disc.equals(laptop.disc)) return false;
        if (!graphicCard.equals(laptop.graphicCard)) return false;
        if (!os.equals(laptop.os)) return false;
        return driveType.equals(laptop.driveType);
    }

    @Override
    public int hashCode() {
        int result = manufacturer.hashCode();
        result = 31 * result + screen.hashCode();
        result = 31 * result + processor.hashCode();
        result = 31 * result + ramSize.hashCode();
        result = 31 * result + disc.hashCode();
        result = 31 * result + graphicCard.hashCode();
        result = 31 * result + os.hashCode();
        result = 31 * result + driveType.hashCode();
        return result;
    }

    public static Laptop checkObjectFields(Laptop item) {
        String defValue = "Brak Informacji";
        if(item.getScreen() == null){
            item.screen = Screen.builder().size(defValue).resolution(defValue).type(defValue).touch(defValue).build();
        }
        else item = Screen.checkObjectFields(item);
        if(item.getProcessor() == null){
            item.processor = Processor.builder().name(defValue).physicalCores(0).clockSpeed(defValue).build();
        }
        else item = Processor.checkObjectFields(item);
        if(item.getDisc() == null){
            item.disc = Disc.builder().storage(defValue).type(defValue).build();
        }
        else item = Disc.checkObjectFields(item);
        if(item.getGraphicCard() == null){
            item.graphicCard = GraphicCard.builder().name(defValue).memory(defValue).build();
        }
        else item = GraphicCard.checkObjectFields(item);
        return item;
    }
}
