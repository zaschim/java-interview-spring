package com.spscommerce.springboot.example.resources;

import com.spscommerce.springboot.example.model.ExampleBean;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@Service
@Tag(name = OpenApiDef.Group.EXAMPLE)
public class ExampleResource {
    private final ExampleBean exampleBean;

    @Autowired
    public ExampleResource(
            ExampleBean exampleBean
    ) {
        this.exampleBean = exampleBean;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(
            summary = "Get Basic Text Example",
            description = """
                The basic response as text, returns "getValue()" from the exampleBean as a string.
            """,
           responses = {
                    @ApiResponse(responseCode = "200", description = "success")
            }
    )
    public String get() {
        return "From ExampleBean value: " + exampleBean.getValue() + "\n";
    }

    @GET
    @Path("/bean")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Get Basic JSON Example",
            description = """
                The basic response as json, returns entire json body of exampleBean.
            """,
            responses = {
                    @ApiResponse(responseCode = "200", description = "success")
            }
    )
    public ExampleBean getExampleBean() {
        return exampleBean;
    }
}
