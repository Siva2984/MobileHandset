package com.axiom.mobilehandset.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.axiom.mobilehandset.model.SearchCriteria;
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
       // test.setId(2345);
        test.setSim(" eSIM");
        test.setRelease(release);
        List<Handset> handsets = new ArrayList<>();
        handsets.add(test);
        repository.saveAll(handsets);
    }

    @Test
    public void givenLast_whenGettingListOfUsers_thenCorrect() {
        HandsetSpecification spec =
                new HandsetSpecification(new SearchCriteria("sim", "", "eSIM"));
        List<Handset> response = repository.findAll(spec);

        assertEquals(1, response.size());
    }
}