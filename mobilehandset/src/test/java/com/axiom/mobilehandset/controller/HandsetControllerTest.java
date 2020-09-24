package com.axiom.mobilehandset.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.axiom.mobilehandset.model.Handset;
import com.axiom.mobilehandset.model.Release;
import com.axiom.mobilehandset.model.Hardware;
import com.axiom.mobilehandset.repository.HandsetRepository;
import com.axiom.mobilehandset.service.HandsetSearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
@ExtendWith(MockitoExtension.class)
class HandsetControllerTest {
    @Mock private HandsetSearchService handsetSearchService;
    @InjectMocks private HandsetController controller;
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private HandsetRepository repository;
    private Handset test;
    private Map<String, String> requestParam;
    @BeforeEach
    void setUp() {
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
        requestParam = new HashMap<String, String>();
        requestParam.put("gps","A-GPS");
        handsetSearchService = new HandsetSearchService(repository);
        controller = new HandsetController(handsetSearchService);
    }


    @Test
    void shouldGetHandsetByHardware() {
        List<Handset> handsets = new ArrayList<>();
        handsets.add(test);

        List<Handset> response = controller.getHandset(requestParam);

        assertEquals("A-GPS", response.get(0).getHardware().getGps());
        assertNotEquals("gps", response.get(0).getHardware().getGps());
    }

    @Test
    void shouldGetHandsetByRelease() {
        List<Handset> handsets = new ArrayList<>();
        handsets.add(test);
        requestParam = new HashMap<String, String>();
        requestParam.put("announceDate","1999");
        requestParam.put("priceEur","200");
        List<Handset> response = controller.getHandset(requestParam);

        assertEquals(" 1999", response.get(0).getRelease().getAnnounceDate());
        assertEquals(200, response.get(0).getRelease().getPriceEur());
        assertNotEquals(" Nano", response.get(0).getRelease().getAnnounceDate());
    }
    @Test
    void shouldGetHandsetBySim() {
        List<Handset> handsets = new ArrayList<>();
        handsets.add(test);
        requestParam = new HashMap<String, String>();
        requestParam.put("sim","eSIM");
        requestParam.put("priceEur","200");
        List<Handset> response = controller.getHandset(requestParam);

        assertEquals(" eSIM", response.get(0).getSim());
        assertNotEquals(" Nano", response.get(0).getSim());
    }




}