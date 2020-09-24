package com.axiom.mobilehandset.service;



import com.axiom.mobilehandset.controller.HandsetController;
import com.axiom.mobilehandset.model.Handset;
import com.axiom.mobilehandset.model.SearchCriteria;
import com.axiom.mobilehandset.repository.HandsetSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.axiom.mobilehandset.repository.HandsetRepository;

import java.util.List;

/**
* Service Implementation
*
 * @author Sivakumar Panchu
 * @version 1.0
 * @since 21/09/2020
 */
@Service
public class HandsetSearchService {
	public static final Logger logger = LoggerFactory.getLogger(HandsetController.class);
	private HandsetRepository handsetRepository;

	@Autowired
	public HandsetSearchService(HandsetRepository handsetRepository) {
		this.handsetRepository = handsetRepository;

	}

	public void saveAll(List<Handset> handsets) {
		 handsetRepository.saveAll(handsets);
	}

	public List<Handset> findAll(Specification handsetspec) {
		return handsetRepository.findAll(handsetspec);
	}
}
