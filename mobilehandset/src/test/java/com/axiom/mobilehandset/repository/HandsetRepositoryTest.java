package com.axiom.mobilehandset.repository;

import static org.junit.jupiter.api.Assertions.*;
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
        release.setPriceEur(200);
        release.setAnnounceDate(" 1999");
        test = new Handset();
        test.setId(2345);
        test.setSim(" eSIM");
        test.setRelease(release);
        List<Handset> handsets = new ArrayList<>();
        handsets.add(test);
        repository.saveAll(handsets);
    }

    @Test
    public void givenSearch_thenExpectedReturned() {
        List<Handset> response = repository.findByPriceEur(200);
        assertEquals(200, response.get(0).getRelease().getPriceEur());
        assertNotEquals(2000, response.get(0).getRelease().getPriceEur());


    }
    @Test
    public void givenSearch_NotAvailable_thenExpectedReturned() {
        List<Handset> response = repository.findByPriceEur(2000);
        assertEquals(0, response.size());
    }
}