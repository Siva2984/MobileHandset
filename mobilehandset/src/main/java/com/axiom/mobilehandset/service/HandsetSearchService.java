package com.axiom.mobilehandset.service;


import com.axiom.mobilehandset.model.Handset;

/**
* Service interface
*
 * @author Sivakumar Panchu
 * @version 1.0
 * @since 21/09/2020
 */
public interface HandsetSearchService {
	
	/**
	 * this method returns account object
     * @param priceEur
     * @return
     */
	public Handset findByPriceEur(long priceEur);
	/**
	 * this method returns handset object
	 * @param sim
	 * @return
	 */
	public Handset findBySim(String sim);
	/**
	 * this method returns handset object
	 * @param announcedDate
	 * @param priceEur
	 * @return
	 */
	public Handset findByAnnouncedDateAndPrice(String announcedDate, long priceEur);
}
