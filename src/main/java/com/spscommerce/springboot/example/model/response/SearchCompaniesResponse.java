package com.spscommerce.springboot.example.model.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.spscommerce.springboot.example.model.Company;

import java.util.List;

public class SearchCompaniesResponse {

    @JsonProperty("companies")
    private List<Company> companies;

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public SearchCompaniesResponse() {}

    public SearchCompaniesResponse(List<Company> companies) {
        this.companies = companies;
    }

}
