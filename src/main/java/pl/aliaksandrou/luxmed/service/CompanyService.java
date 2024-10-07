package pl.aliaksandrou.luxmed.service;

import jakarta.validation.Valid;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.aliaksandrou.luxmed.exceptions.ResourceNotFoundException;
import pl.aliaksandrou.luxmed.model.Company;
import pl.aliaksandrou.luxmed.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class CompanyService implements ICompanyService {

    private static final String ID_MUST_NOT_BE_NULL = "The given id must not be null";

    private final CompanyRepository companyRepository;
    private final Validator validator;

    public CompanyService(CompanyRepository companyRepository, Validator validator) {
        this.companyRepository = companyRepository;
        this.validator = validator;
    }

    @Override
    public Company createCompany(@Valid Company company) {
        if (company == null) {
            throw new IllegalArgumentException("Company cannot be null");
        }
        validator.validate(company);
        log.debug("Creating company: {}", company);
        return companyRepository.save(company);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Company> getCompanyById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException(ID_MUST_NOT_BE_NULL);
        }
        log.debug("Getting company by id: {}", id);
        return companyRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Company> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        log.debug("Getting all companies: {}", companies);
        return companies;
    }

    @Override
    public Company updateCompany(Long id, @Valid Company companyDetails) {
        if (id == null || companyDetails == null) {
            throw new IllegalArgumentException("Id and company details cannot be null");
        }
        validator.validate(companyDetails);
        log.debug("Updating company with id: {} and details: {}", id, companyDetails);
        var company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));

        company.setName(companyDetails.getName());
        if (companyDetails.getDepartments() != null) {
            company.getDepartments().clear();
            company.getDepartments().addAll(companyDetails.getDepartments());
        }
        return companyRepository.save(company);
    }

    @Override
    public void deleteCompany(Long id) {
        if (id == null) {
            throw new IllegalArgumentException(ID_MUST_NOT_BE_NULL);
        }
        log.info("Deleting company with id: {}", id);
        companyRepository.deleteById(id);
    }
}
