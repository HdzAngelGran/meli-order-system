package mx.arkn37.meli.model;

/**
 * Represents a user in the system.
 * <p>
 * Designed for authentication, authorization, and integration with user management tools.
 *
 * @author Angel Hernandez
 */

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

@Entity
@Getter
@Setter
@Audited
@Table(name = "users")
public class User extends ExposedEntity {

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private boolean isEnabled;

}
