package com.spscommerce.springboot.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Company {

    @JsonProperty("companyId")
    private Long companyId;

    @JsonProperty("documentCount")
    private Long documentCount;

    @JsonProperty("companyName")
    private String companyName;

    @JsonProperty("modifiedAt")
    private Date modifiedAt;

    @JsonProperty("modified_by")
    private String modifiedBy;

    @JsonProperty("active")
    private boolean active;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getDocumentCount() {
        return documentCount;
    }

    public void setDocumentCount(Long documentCount) {
        this.documentCount = documentCount;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
