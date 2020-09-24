package com.axiom.mobilehandset.controller;

import com.axiom.mobilehandset.model.Handset;
import com.axiom.mobilehandset.model.SearchCriteria;
import com.axiom.mobilehandset.repository.HandsetSpecification;
import com.axiom.mobilehandset.repository.HandsetSpecificationsBuilder;
import com.axiom.mobilehandset.service.HandsetSearchService;
import com.axiom.mobilehandset.utils.PredicateUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.core.type.TypeReference;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
     * @param handsetSearchCriteria     search criteria from user
     * @return handset list
     */
    @RequestMapping(method = RequestMethod.GET, value="/mobile/search")
    @ResponseBody
    public List<Handset> getHandset(
            @RequestParam Map<String, String> handsetSearchCriteria) {

        try {
            logger.info("handset search requested by user: ", handsetSearchCriteria);
            return Optional.ofNullable(handsetSearchCriteria)
                    .map(this::buildHandsetSpecFromRequestParam)
                    .map(handsetSearchSpec -> handsetSearchService.findAll(handsetSearchSpec))
                    .orElseGet(ArrayList::new)
                    ;
        } catch (Exception e) {
            logger.error("Error while fetching statement", e);
            return null;
        }
    }

    /**
     *
     * @param handsetSearchCriteria
     * @return Specification
     */
    private Specification buildHandsetSpecFromRequestParam(Map<String, String> handsetSearchCriteria) {
        HandsetSpecificationsBuilder handsetSpecification = new HandsetSpecificationsBuilder();

        if (handsetSearchCriteria.containsKey("sim")) {
            handsetSpecification.with("sim", "", handsetSearchCriteria.get("sim"));
        }
        if (handsetSearchCriteria.containsKey("phone")) {
            handsetSpecification.with("phone", "", handsetSearchCriteria.get("phone"));
        }
        if (handsetSearchCriteria.containsKey("brand")) {
            handsetSpecification.with("brand", "", handsetSearchCriteria.get("brand"));
        }
        if (handsetSearchCriteria.containsKey("picture")) {
            handsetSpecification.with("picture", "", handsetSearchCriteria.get("picture"));
        }
        if (handsetSearchCriteria.containsKey("resolution")) {
            handsetSpecification.with("resolution", "", handsetSearchCriteria.get("resolution"));
        }
        if (handsetSearchCriteria.containsKey("announceDate")) {
            handsetSpecification.with("release", "announceDate", handsetSearchCriteria.get("announceDate"));
        }
        if (handsetSearchCriteria.containsKey("priceEur")) {
            handsetSpecification.with("release", "priceEur", handsetSearchCriteria.get("priceEur"));
        }
        if (handsetSearchCriteria.containsKey("audioJack")) {
            handsetSpecification.with("hardware", "audioJack", handsetSearchCriteria.get("audioJack"));
        }
        if (handsetSearchCriteria.containsKey("gps")) {
            handsetSpecification.with("hardware", "gps", handsetSearchCriteria.get("gps"));
        }
        if (handsetSearchCriteria.containsKey("battery")) {
            handsetSpecification.with("hardware", "battery", handsetSearchCriteria.get("battery"));
        }
        return handsetSpecification.build();

    }

}
