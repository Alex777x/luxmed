package pl.aliaksandrou.luxmed.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, length = 128)
    private String name;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private ContactInformation contactInformation;
}
