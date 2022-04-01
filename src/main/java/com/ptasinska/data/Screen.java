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
public class Screen {
    @XmlAttribute
    protected String touch;
    @XmlElement
    protected String size;
    @XmlElement
    protected String resolution;
    @XmlElement
    protected String type;

    public static Laptop checkObjectFields(Laptop item) {
        String defValue = "Brak informacji";
        if(item.getScreen().touch == null || item.getScreen().touch.trim().isEmpty())
            item.getScreen().touch = defValue;
        if(item.getScreen().resolution == null || item.getScreen().resolution.trim().isEmpty())
            item.getScreen().resolution = defValue;
        if(item.getScreen().size == null || item.getScreen().size.trim().isEmpty())
            item.getScreen().size = defValue;
        if(item.getScreen().type == null || item.getScreen().type.trim().isEmpty())
            item.getScreen().type = defValue;
        return item;
    }
}
