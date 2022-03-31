package com.ptasinska.data;

import jakarta.xml.bind.annotation.*;
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

    public Laptop(int id, String[] data) {
        for(int i=0;i<data.length;i++){
            if(data[i] == null) data[i] = "Brak informacji";
            else if(data[i].trim().isEmpty()) data[i]="Brak informacji";
        }
        this.id = id;
        this.manufacturer = data[0];
        this.screen.size = data[1];
        this.screen.resolution = data[2];
        this.screen.type = data[3];
        this.screen.touch = data[4];
        this.processor.name = data[5];
        if(!data[6].equals("Brak informacji"))
            this.processor.physicalCores = Integer.parseInt(data[6]);
        else this.processor.physicalCores = 0;
        this.processor.clockSpeed = data[7];
        this.ramSize = data[8];
        this.disc.storage = data[9];
        this.disc.type = data[10];
        this.graphicCard.name = data[11];
        this.graphicCard.memory = data[12];
        this.os = data[13];
        this.driveType = data[14];
    }

    //new row
    public Laptop(int id) {
        String[] data = new String[15];
        for(int i=0;i<data.length;i++){
            data[i]="Brak informacji";
        }
        this.id = id;
        this.manufacturer = data[0];
        this.screen.size = data[1];
        this.screen.resolution = data[2];
        this.screen.type = data[3];
        this.screen.touch = data[4];
        this.processor.name = data[5];
        if(!data[6].equals("Brak informacji"))
            this.processor.physicalCores = Integer.parseInt(data[6]);
        else this.processor.physicalCores = 0;
        this.processor.clockSpeed = data[7];
        this.ramSize = data[8];
        this.disc.storage = data[9];
        this.disc.type = data[10];
        this.graphicCard.name = data[11];
        this.graphicCard.memory = data[12];
        this.os = data[13];
        this.driveType = data[14];
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
}
