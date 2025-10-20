package mx.arkn37.meli.repository;

/**
 * Repository for entities with soft-delete and UUID support.
 * <p>
 * Enables advanced queries and integrations for traceability and business logic.
 *
 * @author Angel Hernandez
 */

import mx.arkn37.meli.model.ExposedEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public interface ExposedRepository<T extends ExposedEntity> extends BaseRepository<T> {

    Optional<T> findByUuidAndDeleteAtIsNull(UUID uuid);

    Optional<T> findByUuid(UUID uuid);

    @Query("SELECT e FROM #{#entityName} e WHERE e.deleteAt IS NULL")
    Page<T> findAllActive(Pageable pageable);

}
