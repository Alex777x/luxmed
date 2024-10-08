package pl.aliaksandrou.luxmed.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false, length = 128)
    private String name;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Project project;
}
