package com.axiom.mobilehandset.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.Embeddable;


@Data
@AllArgsConstructor
@Embeddable
public class Hardware {
    private String audioJack;
    private String gps;
    private String battery;
    public Hardware() { }
}
