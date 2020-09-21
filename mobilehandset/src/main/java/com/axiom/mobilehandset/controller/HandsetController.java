package com.axiom.mobilehandset.controller;

import com.axiom.mobilehandset.model.Handset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.axiom.mobilehandset.service.HandsetSearchService;

import java.util.List;

/**
 * The StatementController to provide the Services to statementReport returns statement list
 *
 * @author Sivakumar Panchu
 * @version 1.0
 * @since 21/09/2020
 */
@RestController
public class HandsetController {
    public static final Logger logger = LoggerFactory.getLogger(HandsetController.class);

    private final HandsetSearchService handsetSearchService;


    @Autowired
    public HandsetController(HandsetSearchService handsetSearchService) {
        this.handsetSearchService = handsetSearchService;

    }

    /**
     * This method is used to get statement report.
     *
     * @param priceEur mobile handset price in euro
     * @param sim      sim compatible with the mobile handset
     * @param announceDate announced date of the mobile handset model
     * @return handset
     */
    @GetMapping("/mobile/search")
    public Handset getHandset(
            @RequestParam(name = "priceEur", required = false) long priceEur,
            @RequestParam(name = "sim", required = false) String sim,
            @RequestParam(name = "announceDate", required = false) String announceDate) {

        try {

            logger.info("handset search requested by user: ", priceEur, sim, announceDate);

            Handset handset = handsetSearchService.findByPriceEur(priceEur);


            return handset;
        } catch (Exception e) {
            logger.error("Error while fetching statement", e);
            return createErrorResponse(e);
        }
    }
    /**
     * This method is used to prepare model and view for Error case.
     * @param exception
     * @return model
     */
    private static Handset createErrorResponse(Exception exception) {
        Handset handset = new Handset();

        return handset;
    }

}
