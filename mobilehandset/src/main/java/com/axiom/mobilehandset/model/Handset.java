package com.axiom.mobilehandset.model;

import lombok.*;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

/** Represents an handset model class
 * @author Sivakumar Panchu
 * @version 1.0
 * @since 21/09/2020
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Handset {
	@Id
	private Integer id;
	private String brand;
	private String phone;
	private String picture;
	private String resolution;
    private String sim;
    @Embedded
	private Release release;
    @Embedded
	private Hardware hardware;



}
