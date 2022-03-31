package com.ptasinska.data;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
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
}
