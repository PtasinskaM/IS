package com.ptasinska.data;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
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
public class Disc {
    @XmlAttribute
    protected String type;
    @XmlElement
    protected String storage;

    public static Laptop checkObjectFields(Laptop item) {
        String defValue = "Brak informacji";
        if(item.getDisc().type == null || item.getDisc().type.trim().isEmpty())
            item.getDisc().type = defValue;
        if( item.getDisc().storage == null || item.getDisc().storage.trim().isEmpty())
            item.getDisc().storage = defValue;
        return item;
    }
}
