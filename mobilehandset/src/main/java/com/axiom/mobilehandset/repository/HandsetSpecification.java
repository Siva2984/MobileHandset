package com.axiom.mobilehandset.repository;

import com.axiom.mobilehandset.model.Handset;
import com.axiom.mobilehandset.model.Hardware;
import com.axiom.mobilehandset.model.Release;
import com.axiom.mobilehandset.model.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class HandsetSpecification implements Specification<Handset> {


    private SearchCriteria criteria;

    public HandsetSpecification(final SearchCriteria criteria) {
        super();
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate
            (Root<Handset> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        Path rootPath = root.get(criteria.getKey());
        if (rootPath.getJavaType() == Release.class || rootPath.getJavaType() == Hardware.class) {
            if (rootPath.get(criteria.getEmbeddedKey()).getJavaType() == String.class) {
                return builder.like(
                        rootPath.get(criteria.getEmbeddedKey()), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(rootPath.get(criteria.getEmbeddedKey()), criteria.getValue());
            }
        } else if (rootPath.getJavaType() == String.class) {
            return builder.like(
                    root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
        } else {
            return builder.equal(rootPath, criteria.getValue());
        }
    }

}

