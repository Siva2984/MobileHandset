package com.axiom.mobilehandset.repository;

import com.axiom.mobilehandset.model.Handset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HandsetRepository extends JpaRepository<Handset, Long> {
    //custom query added to handle the search based on query param.
    @Query("select h from Handset h where h.sim like %?1")
    List<Handset> findBySimIgnoreCase(String sim);
    @Query("select h from Handset h where h.release.priceEur = ?1")
    List<Handset> findByPriceEur(Integer  priceEur);
    @Query("select h from Handset h where h.release.announceDate like %?1 and h.release.priceEur = ?2")
    List<Handset> findByAnnounceDateAndPriceEur(String announcedDate, Integer priceEur);
}
