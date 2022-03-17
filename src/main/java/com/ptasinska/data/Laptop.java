package com.ptasinska.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Laptop {
    private int id;
    private String manufacturer;
    private String diagonal;
    private String resolution;
    private String surface;
    private String touchscreen;
    private String cpu;
    private String cpuCores;
    private String clockspeed;
    private String ramSize;
    private String diskSize;
    private String diskType;
    private String gpu;
    private String gpuMemory;
    private String os;
    private String driveType;

    public Laptop(int id, String[] data) {
        this.id = id;
        this.manufacturer = data[0];
        this.diagonal = data[1];
        this.resolution = data[2];
        this.surface = data[3];
        this.touchscreen = data[4];
        this.cpu = data[5];
        this.cpuCores = data[6];
        this.clockspeed = data[7];
        this.ramSize = data[8];
        this.diskSize = data[9];
        this.diskType = data[10];
        this.gpu = data[11];
        this.gpuMemory = data[12];
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
        this.diagonal = data[1];
        this.resolution = data[2];
        this.surface = data[3];
        this.touchscreen = data[4];
        this.cpu = data[5];
        this.cpuCores = data[6];
        this.clockspeed = data[7];
        this.ramSize = data[8];
        this.diskSize = data[9];
        this.diskType = data[10];
        this.gpu = data[11];
        this.gpuMemory = data[12];
        this.os = data[13];
        this.driveType = data[14];
    }

    public void setValueAt(int column, String value){
        if(value.trim().isEmpty()) value="Brak informacji";
        switch (column){
            case 1: setManufacturer(value); break;
            case 2: setDiagonal(value); break;
            case 3: setResolution(value); break;
            case 4: setSurface(value); break;
            case 5: setTouchscreen(value); break;
            case 6: setCpu(value); break;
            case 7: setCpuCores(value); break;
            case 8: setClockspeed(value); break;
            case 9: setRamSize(value); break;
            case 10: setDiskSize(value); break;
            case 11: setDiskType(value); break;
            case 12: setGpu(value); break;
            case 13: setGpuMemory(value); break;
            case 14: setOs(value); break;
            case 15: setDriveType(value); break;
        }
    }

    @Override
    public String toString() {
        return manufacturer + ';' +
                diagonal + ';' +
                resolution + ';' +
                surface + ';' +
                touchscreen + ';' +
                cpu + ';' +
                cpuCores + ';' +
                clockspeed + ';' +
                ramSize + ';' +
                diskSize + ';' +
                diskType + ';' +
                gpu + ';' +
                gpuMemory + ';' +
                os + ';' +
                driveType + ';';
    }
}
