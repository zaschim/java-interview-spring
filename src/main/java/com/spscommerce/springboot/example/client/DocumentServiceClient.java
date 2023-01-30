package com.spscommerce.springboot.example.client;

import com.spscommerce.springboot.example.Config;
import com.spscommerce.springboot.example.model.request.PatchDocument;
import com.spscommerce.springboot.example.model.response.GetDocumentsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

/*
    DocumentService is a microservice that stores information about trading documents. All trading documents
    are owned by a given company.

    API endpoints:
    GET /v1/documents Search all documents given optional filter parameters (documentStatus, createdAt, createdBy, modifiedAt)
    GET /v1/documents/{documentId} Retrieve a single document by unique documentId
    POST /v1/documents/{companyId} Create a new document for the given companyId
    PATCH /v1/documents/{documentId} Edit an existing document by unique documentId
    PUT /v1/documents/{companyId} Replace all documents for the given companyId

 */
@Service
public class DocumentServiceClient {

    private final Logger logger = LoggerFactory.getLogger(DocumentServiceClient.class);
    private final Client client;
    private final String apiKey;

    @Autowired
    public DocumentServiceClient(Config config) {
        this.client = ClientBuilder.newClient();
        this.apiKey = config.getDocServiceApiKey();
    }

    public GetDocumentsResponse getDocumentsByCompanyId(String companyId) {
        logger.info("Getting documents by company id");
        return this.client
                .target("https://company-service.in")
                .path("/v1/documents")
                .queryParam("companyId", companyId)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", this.apiKey)
                .get(GetDocumentsResponse.class);
    }

    public void updateDocument(String documentId, PatchDocument patchDocument) {
        logger.info("Updating document by document id");
        this.client.target("https://company-service.in")
                .path("/v1/documents/" + documentId)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", this.apiKey)
                .method(HttpMethod.PATCH, Entity.json(patchDocument));
    }
}
