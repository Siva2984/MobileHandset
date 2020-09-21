package com.axiom.mobilehandset.controller;

import com.axiom.mobilehandset.model.Handset;
import com.axiom.mobilehandset.service.HandsetSearchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.type.TypeReference;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

/**
 * The HandsetController to provide the Services to HandsetController returns handset
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
        System.out.println("handset search requested by user: ");
        this.handsetSearchService = handsetSearchService;
       // loadData(handsetSearchService);
    }
    // read json and write to inmemory database
    @PostConstruct
    private  void loadData() {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Handset>> typeReference = new TypeReference<List<Handset>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/handset.json");
        try {
            List<Handset> handsets = mapper.readValue(inputStream, typeReference);
            System.out.println("Total Handsets to Save" + handsets.size());
            handsetSearchService.saveAll(handsets);
            System.out.println("Handsets Saved successfully!");
        } catch (IOException e) {
            System.out.println("Unable to save users: " + e.getMessage());
        }
    }

    /**
     * This method is used to get statement report.
     *
     * @param priceEur     mobile handset price in euro
     * @param sim          sim compatible with the mobile handset
     * @param announceDate announced date of the mobile handset model
     * @return handset
     */
    @GetMapping("/mobile/search")
    public Iterable<Handset> getHandset(
            @RequestParam(name = "priceEur", required = false) String priceEur,
            @RequestParam(name = "sim", required = false) String sim,
            @RequestParam(name = "announceDate", required = false) String announceDate) {

        try {
            System.out.println("handset search requested by user: ");
            System.out.println("handset search requested by user: " + priceEur);
            System.out.println("handset search requested by user: " + sim);
            System.out.println("handset search requested by user: " + announceDate);
            logger.info("handset search requested by user: ", priceEur, sim, announceDate);

            return handsetSearchService.findByPriceEur(Optional.ofNullable(priceEur).map(Integer::parseInt).orElse(0));

        } catch (Exception e) {
            logger.error("Error while fetching statement", e);
            return null;
        }
    }


}
