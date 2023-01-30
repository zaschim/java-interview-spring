package com.spscommerce.springboot.example.resources;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;

@OpenAPIDefinition(
        info = @Info(
                title = "Spring-Boot-Example-Application",
                version = "1.0",
                description = "An application that demonstrates using sps-java-shared with spring-boot modules"
        ),
        tags = {
                @Tag(name = OpenApiDef.Group.EXAMPLE, description = "Basic example endpoints"),
        }
)
public final class OpenApiDef {
    private OpenApiDef() {
    }

    public static final class Group {
        public static final String EXAMPLE = "Example";

        private Group() {
        }
    }
}
