package pl.aliaksandrou.luxmed.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Schema(description = "Represents contact information of a person")
public class ContactInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the contact information", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotEmpty
    @NotNull(message = "Email is required")
    @Email(message = "Email should be valid")
    @Size(max = 128, message = "Email must be less than 128 characters")
    @Schema(description = "Email of the person", example = "test@test.org", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @NotEmpty
    @NotNull(message = "Phone number is required")
    @Size(max = 20, message = "Phone number must be less than 20 characters")
    @Schema(description = "Phone number of the person", example = "+48123456789", requiredMode = Schema.RequiredMode.REQUIRED)
    private String phone;
}
