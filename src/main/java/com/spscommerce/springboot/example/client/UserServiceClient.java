package com.spscommerce.springboot.example.client;

import com.spscommerce.springboot.example.Config;
import com.spscommerce.springboot.example.model.request.PatchUser;
import com.spscommerce.springboot.example.model.response.GetUsersResponse;
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
    UserService is a microservice that stores information about user/customer permissions. All users
    are owned by a given company.

    API endpoints:
    GET /v1/{companyId}/users Retrieve all users for a given companyId
    POST /v1/{companyId}/users Create a new user for the given companyId
    PATCH /v1/{companyId}/users/{userId} Edit an existing user by unique userId
    PUT /v1/{companyId}/users Replace all users for the given companyId

 */
@Service
public class UserServiceClient {

    private final Logger logger = LoggerFactory.getLogger(DocumentServiceClient.class);
    private final Client client;
    private final String apiKey;

    public UserServiceClient(Config config) {
        this.client = ClientBuilder.newClient();
        this.apiKey = config.getUserServiceApiKey();
    }

    public GetUsersResponse get(String companyId) {
        logger.info("Getting users by company id");
        return this.client
                .target("https://user-service.in")
                .path("/v1/" + companyId + "/users")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", this.apiKey)
                .get(GetUsersResponse.class);
    }

    public void update(String companyId, String userId, PatchUser patchUser) {
        logger.info("Updating user by user id");
        this.client.target("https://user-service.in")
                .path("/v1/" + companyId + "/users/" + userId)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", this.apiKey)
                .method(HttpMethod.PATCH, Entity.json(patchUser));
    }

}
