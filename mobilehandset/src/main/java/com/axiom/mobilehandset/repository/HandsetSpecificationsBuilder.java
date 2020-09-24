package com.axiom.mobilehandset.repository;

import com.axiom.mobilehandset.model.SearchCriteria;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.axiom.mobilehandset.model.Handset;
import org.springframework.data.jpa.domain.Specification;

public class HandsetSpecificationsBuilder {

    private final List<SearchCriteria> params;

    public HandsetSpecificationsBuilder() {
        params = new ArrayList<>();
    }

    public HandsetSpecificationsBuilder with(String key, String embeddedKey, Object value) {
        params.add(new SearchCriteria(key,  embeddedKey, value));
        return this;
    }

    public Specification<Handset> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification> specs = params.stream()
                .map(HandsetSpecification::new)
                .collect(Collectors.toList());

        Specification result = specs.get(0);

        for (int i = 1; i < params.size(); i++) {
            result =  Specification.where(result)
                    .and(specs.get(i));
        }
        return result;
    }
}
