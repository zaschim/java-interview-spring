package com.spscommerce.springboot.example.client;

import com.spscommerce.springboot.example.model.Company;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DatabaseClient {

    public List<Company> getCompanies() {
        List<Company> companies = new ArrayList<>();

        return companies;
    }

    public Company getCompany(Long companyId) {
        Company company = new Company();
        return company;
    }

}
