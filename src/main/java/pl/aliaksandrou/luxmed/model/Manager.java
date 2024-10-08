package pl.aliaksandrou.luxmed.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Schema(description = "Represents a manager with contact information")
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the manager", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotEmpty
    @Column(nullable = false, length = 128)
    @Schema(description = "Name of the manager", example = "John Doe", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "Contact information of the manager")
    private ContactInformation contactInformation;
}
