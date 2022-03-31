package com.ptasinska.data;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class GraphicCard {
    @XmlElement
    protected String name;
    @XmlElement
    protected String memory;
}
