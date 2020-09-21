package com.axiom.mobilehandset.dao;

import com.axiom.mobilehandset.model.Handset;

/**
 * Service interface
 *
 * @author Sivakumar Panchu
 * @version 1.0
 * @since 21/09/2020
 */
public interface HandsetDAO {
	
	/**
	 * this method returns account object
     * @param accountNumber
     * @return
     */
	Handset findByAccountNumber(String accountNumber);
}
