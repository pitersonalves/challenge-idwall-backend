package com.wanted.idwall.controller;

import com.wanted.idwall.service.RefreshDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/refresh")
@AllArgsConstructor
@Tag(name = "API Refresh Base Application")
public class RefreshDataController {

    private final RefreshDataService refreshDataService;

    @GetMapping()
    @Operation(summary = "Get all person wanted on the base FBI and Interpool to save on own base", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns the successful registration of the people " +
                    "searched for in the database"),
    })
    public void refresh() {
       refreshDataService.refresh();
    }

}
