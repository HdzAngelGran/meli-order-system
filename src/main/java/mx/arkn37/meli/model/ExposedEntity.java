package mx.arkn37.meli.model;

import com.github.f4b6a3.uuid.UuidCreator;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public class ExposedEntity extends BaseEntity {

    @NotNull
    @Column(unique = true, updatable = false)
    private UUID uuid;

    @PrePersist
    protected void onCreate() {
        if (this.uuid == null)
            this.uuid = UuidCreator.getTimeOrderedEpoch();
    }

}
