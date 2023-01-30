package com.spscommerce.springboot.example.controller;

import com.spscommerce.springboot.example.client.DatabaseClient;
import com.spscommerce.springboot.example.client.DocumentServiceClient;
import com.spscommerce.springboot.example.client.UserServiceClient;
import com.spscommerce.springboot.example.model.Company;
import com.spscommerce.springboot.example.model.Document;
import com.spscommerce.springboot.example.model.User;
import com.spscommerce.springboot.example.model.request.PatchDocument;
import com.spscommerce.springboot.example.model.request.PatchUser;
import com.spscommerce.springboot.example.model.response.GetDocumentsResponse;
import com.spscommerce.springboot.example.model.response.SearchCompaniesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Validated
@RestController
@RequestMapping("/v1/companies")
public class CompanyController {

    private final DatabaseClient databaseClient;
    private final DocumentServiceClient documentServiceClient;
    private final UserServiceClient userServiceClient;

    @Autowired
    public CompanyController(DatabaseClient databaseClient, DocumentServiceClient documentServiceClient, UserServiceClient userServiceClient) {
        this.databaseClient = databaseClient;
        this.documentServiceClient = documentServiceClient;
        this.userServiceClient = userServiceClient;
    }

    /*
        Return a list of companies with the total count of documents created by the requesting user
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<SearchCompaniesResponse> searchCompanies(@RequestHeader(HttpHeaders.USER_AGENT) String username) {
        List<Company> companies = databaseClient.getCompanies();
        for (Company company : companies) {
            List<Document> docs = new ArrayList<>();
            GetDocumentsResponse r = documentServiceClient.getDocumentsByCompanyId(company.getCompanyId().toString());
            for (Document doc : r.getDocuments()) {
                if (doc.getCreatedBy().equals(username)) {
                    docs.add(doc);
                }
            }
            company.setDocumentCount(docs.stream().count());
        }
        SearchCompaniesResponse searchCompaniesResponse = new SearchCompaniesResponse(companies);
        return new ResponseEntity<>(searchCompaniesResponse, HttpStatus.OK);
    }

    /*
        Merge two companies. Update their documents and users accordingly.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<String> mergeCompanies(@QueryParam("company1") Long company1, @QueryParam("company2") Long company2) {
        Company oldCompany = databaseClient.getCompany(company1);
        Company newCompany = databaseClient.getCompany(company2);

        List<User> oldUsers = userServiceClient.get(oldCompany.getCompanyId().toString()).getUsers();
        for (User oldUser : oldUsers) {
            PatchUser patchUser = new PatchUser(newCompany.getCompanyId().toString());
            userServiceClient.update(oldCompany.getCompanyId().toString(), oldUser.getUserId().toString(), patchUser);
        }
        List<Document> oldDocuments = documentServiceClient.getDocumentsByCompanyId(oldCompany.getCompanyId().toString()).getDocuments();
        for (Document oldDocument : oldDocuments) {
            PatchDocument patchDocument = new PatchDocument(newCompany.getCompanyId().toString());
            documentServiceClient.updateDocument(oldDocument.getDocumentId().toString(), patchDocument);
        }

        return new ResponseEntity<>("completed", HttpStatus.OK);
    }

}
