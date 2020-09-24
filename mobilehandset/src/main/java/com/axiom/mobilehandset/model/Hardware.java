package com.axiom.mobilehandset.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Hardware  implements Serializable {
    private String audioJack;
    private String gps;
    private String battery;
}
