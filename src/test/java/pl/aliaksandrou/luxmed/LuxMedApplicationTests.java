package pl.aliaksandrou.luxmed;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import pl.aliaksandrou.luxmed.controller.CompanyController;
import pl.aliaksandrou.luxmed.service.CompanyService;

import static org.assertj.core.api.Assertions.assertThat;


@ActiveProfiles("test")
@SpringBootTest
class LuxMedApplicationTests {

    @Autowired
    private CompanyController companyController;

    @Autowired
    private CompanyService companyService;

    @Test
    void contextLoads() {
        assertThat(companyController).isNotNull();
        assertThat(companyService).isNotNull();
    }
}
