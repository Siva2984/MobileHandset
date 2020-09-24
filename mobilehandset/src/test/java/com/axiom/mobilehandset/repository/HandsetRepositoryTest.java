package com.axiom.mobilehandset.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.axiom.mobilehandset.model.Hardware;
import com.axiom.mobilehandset.model.SearchCriteria;
import com.axiom.mobilehandset.service.HandsetSearchService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.axiom.mobilehandset.model.Handset;
import com.axiom.mobilehandset.model.Release;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
@ExtendWith(MockitoExtension.class)
class HandsetRepositoryTest {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private HandsetRepository repository;
    private Handset test;
    @BeforeEach
    public void before() {
        Release release = new Release();
        Hardware hardware = new Hardware();
        hardware.setGps("A-GPS");
        hardware.setBattery("Battery");
        hardware.setAudioJack("audiojack");
        release.setPriceEur(200);
        release.setAnnounceDate(" 1999");
        test = new Handset();
        test.setId(2345);
        test.setBrand("Samsung S9");
        test.setHardware(hardware);
        test.setResolution("15");
        test.setPicture("front and back");
        test.setSim(" eSIM");
        test.setRelease(release);
        List<Handset> handsets = new ArrayList<>();
        handsets.add(test);
        repository.saveAll(handsets);

    }

    @Test
    public void givenSim_whenGettingListOfHandsets_thenCorrect() {
        HandsetSpecification spec =
                new HandsetSpecification(new SearchCriteria("sim", "", "eSIM"));
        List<Handset> response = repository.findAll(spec);

        assertEquals(1, response.size());
    }

    @Test
    public void givenGps_whenGettingListOfHandsets_thenCorrect() {
        HandsetSpecification spec =
                new HandsetSpecification(new SearchCriteria("hardware", "gps", "A-GPS"));
        List<Handset> response = repository.findAll(spec);

        assertEquals(1, response.size());
    }

    @Test
    public void givenDate_whenGettingListOfHandsets_thenCorrect() {
        HandsetSpecification spec =
                new HandsetSpecification(new SearchCriteria("release", "announceDate", "1999"));
        List<Handset> response = repository.findAll(spec);

        assertEquals(1, response.size());
    }

    @Test
    public void givenBrand_whenGettingListOfHandsets_thenCorrect() {
        HandsetSpecification spec =
                new HandsetSpecification(new SearchCriteria("brand", "", "S9"));
        List<Handset> response = repository.findAll(spec);

        assertEquals(1, response.size());
    }
}