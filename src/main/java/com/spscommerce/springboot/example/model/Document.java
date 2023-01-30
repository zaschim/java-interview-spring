package com.spscommerce.springboot.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Document {

    @JsonProperty("documentId")
    private Long documentId;

    @JsonProperty("documentStatus")
    private DocumentStatus documentStatus;

    @JsonProperty("createdAt")
    private Date createdAt;

    @JsonProperty("createdBy")
    private String createdBy;

    @JsonProperty("modifiedAt")
    private Date modifiedAt;

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public DocumentStatus getDocumentStatus() {
        return documentStatus;
    }

    public void setDocumentStatus(DocumentStatus documentStatus) {
        this.documentStatus = documentStatus;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
