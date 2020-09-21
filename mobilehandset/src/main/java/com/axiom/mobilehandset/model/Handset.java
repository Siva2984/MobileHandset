package com.axiom.mobilehandset.model;

import lombok.Getter;
import lombok.Setter;

/** Represents an handset model class
 * @author Sivakumar Panchu
 * @version 1.0
 * @since 21/09/2020
*/

public class Handset {


	private long priceEur;
    private String sim;
	private String announceDate;
    
    /** 
    * Creates an handset with the specified name
    */
	public Handset() { }
	
	/** Creates an account with the specified name.
	 * @param priceEur The handset price.
	 * @param sim The handset supported  sim.
	 * @param announceDate The handset announced date.
	*/
	public Handset(long priceEur, String sim, String announceDate) {
		this.priceEur = priceEur;
		this.sim = sim;
		this.announceDate = announceDate;
	}
	public long getPriceEur() {
		return priceEur;
	}

	public void setPriceEur(long priceEur) {
		this.priceEur = priceEur;
	}

	public String getSim() {
		return sim;
	}

	public void setSim(String sim) {
		this.sim = sim;
	}
	public String getAnnounceDate() {
		return announceDate;
	}

	public void setAnnounceDate(String announceDate) {
		this.announceDate = announceDate;
	}

}
