package pl.aliaksandrou.luxmed.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Schema(description = "Represents a department with teams")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the department", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotEmpty
    @Column(nullable = false, length = 128)
    @Schema(description = "Name of the department", example = "HR", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "List of teams associated with the department")
    private List<Team> teams;
}
