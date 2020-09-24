package com.axiom.mobilehandset.repository;

import com.axiom.mobilehandset.model.Handset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HandsetRepository extends JpaRepository<Handset, Long>, JpaSpecificationExecutor<Handset> {
}
