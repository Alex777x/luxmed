package pl.aliaksandrou.luxmed.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Entity
@Data
@Schema(description = "Represents a company with departments")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the company", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotEmpty
    @Column(nullable = false, length = 128)
    @Schema(description = "Name of the company", example = "Acme Corporation", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "List of departments associated with the company")
    private List<Department> departments;
}
