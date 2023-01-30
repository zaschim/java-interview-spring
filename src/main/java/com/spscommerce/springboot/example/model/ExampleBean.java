package com.spscommerce.springboot.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExampleBean {
    private final String value;

    public ExampleBean(@JsonProperty("value") String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
