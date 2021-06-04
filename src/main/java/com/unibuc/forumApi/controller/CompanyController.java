package com.unibuc.forumApi.controller;

import com.unibuc.forumApi.config.Pagination;
import com.unibuc.forumApi.dto.CompanyRequest;
import com.unibuc.forumApi.mapper.CompanyMapper;
import com.unibuc.forumApi.model.Company;
import com.unibuc.forumApi.service.CompanyService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;
    private final CompanyMapper companyMapper;

    public CompanyController(
            CompanyService companyService,
            CompanyMapper companyMapper) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Company> getCompany(@PathVariable int id) {
        return companyService.getCompany(id);
    }

    @GetMapping
    @ResponseBody
    public List<Company> getCompanies(Integer page, Integer size, String sort) {
        return new Pagination<>(companyService.getCompanies(), page, size, sort)
                .paginate(Comparator.comparing(Company::getName));
    }

    @PostMapping
    @ApiOperation(value = "Create a Company",
            notes = "Creates a new Company based on the information received in the request")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Company was successfully created based on the received request"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public ResponseEntity<Company> createCompany(@RequestBody CompanyRequest companyRequest) {
        Company mappedCompany = companyMapper.companyRequestToCompany(companyRequest);
        Company savedCompany = companyService.create(mappedCompany);
        return ResponseEntity.created(URI.create("/companies/" + savedCompany.getId()))
                .body(savedCompany);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable int id, @RequestBody CompanyRequest companyRequest) {
        Company mappedCompany = companyMapper.companyRequestToCompany(companyRequest);
        mappedCompany.setId(id);
        Company savedCompany = companyService.update(mappedCompany);
        return ResponseEntity.created(URI.create("/companies/" + savedCompany.getId()))
                .body(savedCompany);
    }

    @DeleteMapping("/{id}")
    public void removeCompany(@PathVariable int id) {
        companyService.removeCompany(id);
    }

}
