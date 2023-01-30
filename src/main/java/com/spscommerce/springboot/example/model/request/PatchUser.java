package com.spscommerce.springboot.example.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PatchUser {

    @JsonProperty("companyId")
    private String companyId;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public PatchUser() {}

    public PatchUser(String companyId) {
        this.companyId = companyId;
    }

}
