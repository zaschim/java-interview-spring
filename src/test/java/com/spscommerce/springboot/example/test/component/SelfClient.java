package com.spscommerce.springboot.example.test.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spscommerce.shared.springboot.jaxrs.JerseyJacksonFeature;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.util.concurrent.TimeUnit;

/**
 * Provides a bean that will configure the jax-rs jersey client with jackson
 * and will set up a way to get a target that targets the server booted by {@link SpringBootTest}
 * so tests don't have to fuss about that.
 * <p>
 * This is a bit trickier than a normal bean since normal configuration classes start before
 * the server is booted and the port is known, so it hooks into the special spring
 * {@link ServletWebServerInitializedEvent}.
 */
@Component
public class SelfClient implements ApplicationListener<ServletWebServerInitializedEvent> {

    private int localPort;
    private WebTarget selfTarget;

    @Override
    public void onApplicationEvent(ServletWebServerInitializedEvent event) {
        //uses whatever the default ObjectMapper that the app makes for us, which is probably what we want
        final var mapper = event.getApplicationContext().getBean(ObjectMapper.class);
        localPort = event.getWebServer().getPort();
        selfTarget = createBaseTarget(localPort, mapper);
    }

    public int getLocalPort() {
        return localPort;
    }

    public WebTarget getSelfTarget() {
        return selfTarget;
    }

    private static WebTarget createBaseTarget(int localPort, ObjectMapper mapper) {
        return ClientBuilder.newBuilder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .register(new JerseyJacksonFeature(mapper))
                .build()
                .target(createLocalUrl(localPort));
    }

    /**
     * @return local url for spring app, just have to pick a port
     */
    public static String createLocalUrl(int port) {
        return "http://localhost:" + port + "/";
    }
}
