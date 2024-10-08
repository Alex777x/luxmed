package pl.aliaksandrou.luxmed.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.aliaksandrou.luxmed.exceptions.ResourceNotFoundException;
import pl.aliaksandrou.luxmed.model.Company;
import pl.aliaksandrou.luxmed.service.ICompanyService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/companies")
@Tag(name = "Company", description = "Company management APIs")
public class CompanyController {

    private final ICompanyService companyService;

    public CompanyController(ICompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    @Operation(summary = "Create a new company", description = "Adds a new company to the system")
    @ApiResponse(responseCode = "201", description = "Company created successfully")
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        var savedCompany = companyService.createCompany(company);
        log.info("Company created: {}", savedCompany);
        return new ResponseEntity<>(savedCompany, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get company by ID", description = "Retrieves a company by its ID")
    @ApiResponse(responseCode = "200", description = "Company found")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        var company = companyService.getCompanyById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Get all companies", description = "Retrieves all companies from the system")
    @ApiResponse(responseCode = "200", description = "Companies found")
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update company", description = "Updates a company in the system")
    @ApiResponse(responseCode = "200", description = "Company updated successfully")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody Company companyDetails) {
        var updatedCompany = companyService.updateCompany(id, companyDetails);
        return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete company", description = "Deletes a company from the system")
    @ApiResponse(responseCode = "204", description = "Company deleted successfully")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
