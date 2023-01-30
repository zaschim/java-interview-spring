package com.spscommerce.springboot.example.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spscommerce.springboot.example.model.Document;

import java.util.List;

public class GetDocumentsResponse {

    @JsonProperty("documents")
    private List<Document> documents;

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

}
