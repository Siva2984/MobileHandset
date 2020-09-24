package com.axiom.mobilehandset.model;

import javax.persistence.Embedded;
import javax.persistence.Id;

public class SearchCriteria {

    private String key;
    private Object value;

    private String embeddedKey;

    public SearchCriteria() {

    }

    public SearchCriteria(final String key, final String embeddedKey, final Object value) {
        super();
        this.key = key;
        this.embeddedKey = embeddedKey;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(final String key) {
        this.key = key;
    }


    public Object getValue() {
        return value;
    }

    public void setValue(final Object value) {
        this.value = value;
    }
    public String getEmbeddedKey() {
        return embeddedKey;
    }

    public void setEmbeddedKey(String embeddedKey) {
        this.embeddedKey = embeddedKey;
    }

}
