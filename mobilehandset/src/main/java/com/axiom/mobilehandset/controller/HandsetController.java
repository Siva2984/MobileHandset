package com.axiom.mobilehandset.controller;

import com.axiom.mobilehandset.model.Handset;
import com.axiom.mobilehandset.service.HandsetSearchService;
import com.axiom.mobilehandset.utils.PredicateUtil;
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
        this.handsetSearchService = handsetSearchService;
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
            handsetSearchService.saveAll(handsets);
            logger.info("Static Handsets data loaded successfully!");
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
    public List<Handset> getHandset(
            @RequestParam(name = "priceEur", required = false) String priceEur,
            @RequestParam(name = "sim", required = false) String sim,
            @RequestParam(name = "announceDate", required = false) String announceDate) {

        try {
            logger.info("handset search requested by user: ", priceEur, sim, announceDate);

            return Optional
                    .ofNullable(priceEur)
                    .map(Integer::parseInt)
                    .filter(price -> PredicateUtil.isGreaterThanZero.test(price))
                    .map(price -> {
                        return PredicateUtil.isNullOrEmpty.test(announceDate)?
                        handsetSearchService.findByPriceEur(price) : handsetSearchService.findByAnnounceDateAndPriceEur(announceDate, price);
                    })
                    .orElseGet(() -> Optional
                            .ofNullable(sim)
                            .map(simValue -> handsetSearchService.findBySim(sim))
                            .orElse(null)
                        )
                    ;
        } catch (Exception e) {
            logger.error("Error while fetching statement", e);
            return null;
        }
    }


}
