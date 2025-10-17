package mx.arkn37.meli.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Audited
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean enabled;

    @Column(updatable = false, nullable = false)
    private UUID createdBy;

    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    private UUID updatedBy;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private UUID deleteBy;
    private LocalDateTime deleteAt;
}
