package pl.aliaksandrou.luxmed.controller;

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
public class CompanyController {

    private final ICompanyService companyService;

    public CompanyController(ICompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        var savedCompany = companyService.createCompany(company);
        log.info("Company created: {}", savedCompany);
        return new ResponseEntity<>(savedCompany, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        var company = companyService.getCompanyById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody Company companyDetails) {
        var updatedCompany = companyService.updateCompany(id, companyDetails);
        return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
