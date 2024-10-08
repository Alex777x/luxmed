package pl.aliaksandrou.luxmed.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
@Schema(description = "Represents a project with a manager")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the project", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotEmpty
    @Column(nullable = false, length = 128)
    @Schema(description = "Name of the project", example = "Luxmed", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "Manager of the project")
    private Manager manager;
}
