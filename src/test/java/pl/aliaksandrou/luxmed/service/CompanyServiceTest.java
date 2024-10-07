package pl.aliaksandrou.luxmed.service;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import pl.aliaksandrou.luxmed.model.*;
import pl.aliaksandrou.luxmed.repository.CompanyRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class CompanyServiceTest {

    @Autowired
    private ICompanyService companyService;

    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    public void setUp() {
        companyRepository.deleteAll();
    }

    @Test
    void testCreateCompany() {
        var company = getTestCompany();
        company.setName("Test Company");
        var savedCompany = companyService.createCompany(company);

        assertNotNull(savedCompany.getId());
        assertEquals("Test Company", savedCompany.getName());
    }

    @Test
    void testCreateCompanyNull() {
        assertThrows(IllegalArgumentException.class, () -> companyService.createCompany(null));
    }

    @Test
    void testCreateCompanyNullName() {
        var company = getTestCompany();
        company.setName(null);
        assertThrows(ConstraintViolationException.class, () -> companyService.createCompany(company));
    }

    @Test
    void testGetCompanyById() {
        var company = getTestCompany();
        var savedCompany = companyService.createCompany(company);

        var foundCompany = companyService.getCompanyById(savedCompany.getId()).orElse(null);

        assertNotNull(foundCompany);
        assertEquals(savedCompany.getId(), foundCompany.getId());
        assertEquals(savedCompany.getName(), foundCompany.getName());
    }

    @Test
    void testGetCompanyByIdNull() {
        assertThrows(IllegalArgumentException.class, () -> companyService.getCompanyById(null));
    }

    @Test
    void testGetAllCompanies() {
        var company1 = getTestCompany();
        company1.setName("Test Company 1");
        companyService.createCompany(company1);

        var company2 = getTestCompany();
        company2.setName("Test Company 2");
        companyService.createCompany(company2);

        var companies = companyService.getAllCompanies();

        assertEquals(2, companies.size());
    }

    @Test
    @Transactional
    void testGetAllCompaniesAndAssociations() {
        var company = getTestCompany();
        company.setName("Test Company");
        companyService.createCompany(company);

        var companies = companyService.getAllCompanies();

        assertEquals(1, companies.size());
        Company companiesFirst = companies.getFirst();
        List<Department> departments = companiesFirst.getDepartments();
        assertNotNull(departments);
        assertNotNull(departments.getFirst().getTeams());
        assertNotNull(departments.getFirst().getTeams().getFirst().getProject());
        assertNotNull(departments.getFirst().getTeams().getFirst().getProject().getManager());
        assertNotNull(departments.getFirst().getTeams().getFirst().getProject().getManager().getContactInformation());
    }

    @Test
    void testUpdateCompany() {
        var company = getTestCompany();
        var savedCompany = companyService.createCompany(company);

        savedCompany.setName("Updated Company");
        var updatedCompany = companyService.updateCompany(savedCompany.getId(), savedCompany);

        assertEquals("Updated Company", updatedCompany.getName());
    }

    @Test
    void testUpdateCompanyNullId() {
        var company = getTestCompany();
        var savedCompany = companyService.createCompany(company);

        assertThrows(IllegalArgumentException.class, () -> companyService.updateCompany(null, savedCompany));
    }

    @Test
    void testUpdateCompanyNull() {
        assertThrows(IllegalArgumentException.class, () -> companyService.updateCompany(null, null));
    }

    @Test
    void testUpdateCompanyNullName() {
        var company = getTestCompany();
        var savedCompany = companyService.createCompany(company);

        savedCompany.setName(null);
        assertThrows(Exception.class, () -> companyService.updateCompany(savedCompany.getId(), savedCompany));
    }

    @Test
    void testDeleteCompany() {
        var company = getTestCompany();
        var savedCompany = companyService.createCompany(company);

        companyService.deleteCompany(savedCompany.getId());

        var foundCompany = companyService.getCompanyById(savedCompany.getId()).orElse(null);

        assertNull(foundCompany);
    }

    @Test
    void testDeleteCompanyNull() {
        assertThrows(IllegalArgumentException.class, () -> companyService.deleteCompany(null));
    }

    @Test
    void testInvalidEmail() {
        var company = getTestCompany();
        company.getDepartments().getFirst()
                .getTeams().getFirst()
                .getProject().getManager().getContactInformation().setEmail("invalid-email");

        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            companyService.createCompany(company);
        });

        String expectedMessage = "Email should be valid";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    private Company getTestCompany() {
        var contactInfo = new ContactInformation();
        contactInfo.setEmail("test@test.org");
        contactInfo.setPhone("1234567890");

        var manager = new Manager();
        manager.setName("John Doe");
        manager.setContactInformation(contactInfo);

        var project = new Project();
        project.setName("Test Project");
        project.setManager(manager);

        var team = new Team();
        team.setName("Test Team");
        team.setProject(project);

        var department = new Department();
        department.setName("Test Department");
        department.setTeams(List.of(team));

        var company = new Company();
        company.setName("Test Company");
        company.setDepartments(List.of(department));
        return company;
    }
}
