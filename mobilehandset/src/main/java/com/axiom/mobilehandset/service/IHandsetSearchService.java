package com.axiom.mobilehandset.service;

import com.axiom.mobilehandset.model.Handset;
import java.util.List;

public interface IHandsetSearchService {

    List<Handset> findBySim(String sim);
    List<Handset> findByPriceEur(Integer  priceEur);
    List<Handset> findByAnnounceDateAndPriceEur(String announcedDate, Integer  priceEur);

}
