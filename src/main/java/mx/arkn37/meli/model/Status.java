package mx.arkn37.meli.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

@Entity
@Getter
@Setter
@Audited
@Table(name = "status")
public class Status extends BaseEntity {

    @NotNull
    private String name;
    private String description;

}
