package com.automobiles.web;

import com.automobiles.domain.Automobile;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

@Tag(name = "Automobile", description = "The Automobile API")
public interface AutomobileOpenApi extends AutomobileResource {

    @Operation(summary = "Add a new Automobile", description = "Endpoint for creating an entity", tags = {"Automobile"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Automobile created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Automobile already exists")})
    Automobile saveAutomobile(@Parameter(description = "Automobile", required = true) Automobile automobile);

    @Operation(summary = "Fetch all automobiles", description = "Returns a list of all automobiles", tags = {"Automobile"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Automobile.class))))
    })
    Collection<Automobile> getAllAutomobiles();

    @Operation(summary = "Find automobile by ID", description = "Returns a single automobile", tags = {"Automobile"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(schema = @Schema(implementation = Automobile.class))),
            @ApiResponse(responseCode = "404", description = "There is no such automobile")})
    Automobile getAutomobileById(
            @Parameter(description = "Id of the Automobile to be obtained. Cannot be empty.", required = true)
            @PathVariable Long id);

}
