package pl.aliaksandrou.luxmed.service;

import pl.aliaksandrou.luxmed.model.Company;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing companies.
 */
public interface ICompanyService {
    /**
     * Creates a new company.
     *
     * @param company the company to create
     * @return the created company
     */
    Company createCompany(Company company);

    /**
     * Retrieves a company by its id.
     *
     * @param id the id of the company to retrieve
     * @return the company with the given id or an empty optional if not found
     */
    Optional<Company> getCompanyById(Long id);

    /**
     * Retrieves all companies.
     *
     * @return a list of all companies
     */
    List<Company> getAllCompanies();

    /**
     * Updates a company.
     *
     * @param id             the id of the company to update
     * @param companyDetails the company details to update
     * @return the updated company
     */
    Company updateCompany(Long id, Company companyDetails);

    /**
     * Deletes a company.
     *
     * @param id the id of the company to delete
     */
    void deleteCompany(Long id);
}
