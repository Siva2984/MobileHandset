package com.axiom.mobilehandset.service;


import com.axiom.mobilehandset.model.Handset;
import com.axiom.mobilehandset.model.Hardware;
import com.axiom.mobilehandset.model.Release;
import com.axiom.mobilehandset.repository.HandsetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class HandsetSearchServiceTest {
    @InjectMocks private HandsetSearchService handsetSearchService;
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private HandsetRepository repository;
    private Handset test;
    @BeforeEach
    void setUp() {
        Release release = new Release();
        Hardware hardware = new Hardware();
        hardware.setGps("gps");
        hardware.setBattery("Battery");
        hardware.setAudioJack("audiojack");
        release.setPriceEur(200);
        release.setAnnounceDate(" 1999");
        test = new Handset();
        //test.setId(2345);
        test.setBrand("Samsung S9");
        test.setHardware(hardware);
        test.setResolution("15");
        test.setPicture("front and back");
        test.setSim(" eSIM");
        test.setRelease(release);
        List<Handset> handsets = new ArrayList<>();
        handsets.add(test);
        repository.saveAll(handsets);
        handsetSearchService = new HandsetSearchService(repository);

    }




}