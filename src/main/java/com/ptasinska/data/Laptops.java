package com.ptasinska.data;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "laptops")
@XmlAccessorType(XmlAccessType.FIELD)
public class Laptops {

    @XmlAttribute(name = "moddate")
    String moddate;

    @XmlElement(name = "laptop")
    List<Laptop> laptops;

}