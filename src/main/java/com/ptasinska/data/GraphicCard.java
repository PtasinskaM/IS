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
public class GraphicCard {
    @XmlElement
    protected String name;
    @XmlElement
    protected String memory;

    public static Laptop checkObjectFields(Laptop item) {
        String defValue = "Brak informacji";
        if(item.getGraphicCard().name == null || item.getGraphicCard().name.trim().isEmpty())
            item.getGraphicCard().name = defValue;
        if(item.getGraphicCard().memory == null || item.getGraphicCard().memory.trim().isEmpty())
            item.getGraphicCard().memory = defValue;
        return item;
    }
}
