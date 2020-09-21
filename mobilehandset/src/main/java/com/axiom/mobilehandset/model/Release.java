package com.axiom.mobilehandset.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@Embeddable
public class Release {
    private int priceEur;
    private String announceDate;
    public Release() { }
}
