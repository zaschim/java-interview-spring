package com.spscommerce.springboot.example.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.spscommerce.springboot.example.ExampleApplication;
import com.spscommerce.springboot.example.model.ExampleBean;
import com.spscommerce.springboot.example.test.component.SelfClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;

import javax.ws.rs.client.WebTarget;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(
        classes = ExampleApplication.class,
        webEnvironment = WebEnvironment.RANDOM_PORT
)
class ExampleControllerTest {
    private final WebTarget target;

    @LocalServerPort
    int port;

    @Autowired
    public ExampleControllerTest(SelfClient client) {
        this.target = client.getSelfTarget();
    }

    @Test
    void testGet() {
        assertThat(target.path("/").request().get(String.class)).contains("hellotest");
    }

    @Test
    void testBeanGet() {
        final var bean = target.path("/bean").request().get(ExampleBean.class);
        assertEquals("hellotest", bean.getValue());
    }

    @Test
    void testActuatorWorks() {
        final var res = target.path("/actuator/health").request().get(ObjectNode.class);
        assertEquals("UP", res.get("status").asText());
    }
}
