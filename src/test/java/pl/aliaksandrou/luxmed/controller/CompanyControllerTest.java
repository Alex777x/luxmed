package pl.aliaksandrou.luxmed.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import pl.aliaksandrou.luxmed.model.Company;
import pl.aliaksandrou.luxmed.service.CompanyService;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@WebMvcTest(CompanyController.class)
class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyService companyService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createCompany() throws Exception {
        var company = new Company();
        company.setName("New Company");

        Mockito.when(companyService.createCompany(Mockito.any(Company.class))).thenReturn(company);

        mockMvc.perform(post("/api/companies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(company)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("New Company"));
    }

    @Test
    void testGetCompanyById() throws Exception {
        var company = new Company();
        company.setId(1L);
        company.setName("Test Company");

        Mockito.when(companyService.getCompanyById(1L)).thenReturn(Optional.of(company));

        mockMvc.perform(get("/api/companies/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Company"));
    }

    @Test
    void getAllCompanies() throws Exception {
        var company1 = new Company();
        company1.setName("Company 1");

        var company2 = new Company();
        company2.setName("Company 2");

        Mockito.when(companyService.getAllCompanies()).thenReturn(List.of(company1, company2));

        mockMvc.perform(get("/api/companies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Company 1"))
                .andExpect(jsonPath("$[1].name").value("Company 2"));
    }

    @Test
    void updateCompany() throws Exception {
        var company = new Company();
        company.setId(1L);
        company.setName("Updated Company");

        Mockito.when(companyService.updateCompany(Mockito.eq(1L), Mockito.any(Company.class))).thenReturn(company);

        mockMvc.perform(put("/api/companies/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(company)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Company"));
    }

    @Test
    void deleteCompany() throws Exception {
        Mockito.doNothing().when(companyService).deleteCompany(1L);

        mockMvc.perform(delete("/api/companies/1"))
                .andExpect(status().isNoContent());
    }
}
