package pl.aliaksandrou.luxmed.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class ContactInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Email is required")
    @Email(message = "Email should be valid")
    @Size(max = 128, message = "Email must be less than 128 characters")
    private String email;

    @NotNull(message = "Phone number is required")
    @Size(max = 20, message = "Phone number must be less than 20 characters")
    private String phone;
}
