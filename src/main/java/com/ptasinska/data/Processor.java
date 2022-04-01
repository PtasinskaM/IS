package com.ptasinska.data;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Processor {
    @XmlElement
    protected String name;
    @XmlElement(name = "physical_cores")
    protected int physicalCores;
    @XmlElement(name = "clock_speed")
    protected String clockSpeed;

    public static Laptop checkObjectFields(Laptop item) {
        String defValue = "Brak informacji";
        if(item.getProcessor().name == null || item.getProcessor().name.trim().isEmpty())
            item.getProcessor().name = defValue;
        if(item.getProcessor().clockSpeed == null || item.getProcessor().clockSpeed.trim().isEmpty())
            item.getProcessor().clockSpeed = defValue;
        return item;
    }
}
