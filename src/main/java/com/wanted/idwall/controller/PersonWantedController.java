package com.wanted.idwall.controller;

import com.wanted.idwall.dto.DataViewDTO;
import com.wanted.idwall.dto.PersonWantedDTO;
import com.wanted.idwall.model.PersonWantedFilter;
import com.wanted.idwall.service.PersonWantedService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "API Person Wanted - CRUD")
@RequestMapping(value = "/person-wanted", produces = {"application/json"})
public class PersonWantedController {

    private final PersonWantedService personWantedService;

    @GetMapping()
    @Operation(summary = "Get all person wanted on the base with filters", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return all person wanted on the base"),
    })
    public List<PersonWantedFilter> getAll(@RequestParam(required = false) String name,
                                           @RequestParam(required = false) String nationality,
                                           @RequestParam(required = false) String gender,
                                           @RequestParam(required = false) String base,
                                           @RequestParam(required = false) String classification,
                                           Pageable pageable) {
        return personWantedService.findAll(name, nationality, gender, base, classification, pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get all person wanted on the base by id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return all information about person wanted on the base"),
    })
    public PersonWantedDTO getById(@PathVariable Integer id) {
        return personWantedService.getById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Register new person wanted on the base", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return success register of new person wanted on the base"),
    })
    public PersonWantedDTO create(@RequestBody PersonWantedDTO request) {
        return personWantedService.create(request);
    }

    @PutMapping()
    @Operation(summary = "Update person wanted on the base", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update information of person wanted on the base"),
    })
    public PersonWantedDTO update(@RequestBody PersonWantedDTO request) {
        return personWantedService.update(request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete person wanted on the base", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete person on the base"),
    })
    public void delete(@PathVariable Integer id) {
        personWantedService.delete(id);
    }

    @GetMapping("/populate-data")
    @Operation(summary = "Get data to use the view", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return data to use on the view"),
    })
    public DataViewDTO getData() {
        return personWantedService.findAllDataForView();
    }

}
