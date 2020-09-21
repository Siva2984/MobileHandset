package com.axiom.mobilehandset.service;



import com.axiom.mobilehandset.dao.HandsetDAO;
import com.axiom.mobilehandset.model.Handset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.axiom.mobilehandset.dao.HandsetDAO;
/**
* Service Implementation
*
 * @author Sivakumar Panchu
 * @version 1.0
 * @since 21/09/2020
 */
@Service
public class HandsetSearchServiceImpl implements HandsetSearchService {
	
	private final HandsetDAO handsetDAO;

	@Autowired
	public HandsetSearchServiceImpl(HandsetDAO handsetDAO) {
		this.handsetDAO = handsetDAO;
	}
	
	/**
	 * this method returns handset object
     * @param priceEur
     * @return
     */
	@Override
	public Handset findByPriceEur(long priceEur) {
		return handsetDAO.findByAccountNumber(priceEur+"");
	}

	/**
	 * this method returns handset object
	 * @param sim
	 * @return
	 */
	@Override
	public Handset findBySim(String sim) {
		return handsetDAO.findByAccountNumber(sim);
	}

	/**
	 * this method returns handset object
	 * @param priceEur
	 * @return
	 */
	@Override
	public Handset findByAnnouncedDateAndPrice(String announcedDate, long priceEur) {
		return handsetDAO.findByAccountNumber(announcedDate);
	}
}
