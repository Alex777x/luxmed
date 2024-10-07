package pl.aliaksandrou.luxmed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.aliaksandrou.luxmed.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
