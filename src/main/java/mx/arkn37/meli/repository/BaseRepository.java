package mx.arkn37.meli.repository;

/**
 * Base repository interface for all entities.
 * <p>
 * Extends JpaRepository to provide CRUD and custom queries, supporting integration with Spring Data and digital tools.
 * <ul>
 *   <li>Promotes code reuse and consistency.</li>
 *   <li>Stakeholders: backend developers, data engineers.</li>
 * </ul>
 *
 * @author Angel Hernandez
 */

import mx.arkn37.meli.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, Long> {

    @Query("SELECT e FROM #{#entityName} e WHERE e.deletedAt IS NULL")
    List<T> findAllActive();

    @Query("SELECT e FROM #{#entityName} e WHERE e.id = ?1 AND e.deletedAt IS NULL")
    Optional<T> findActiveById(Long id);

}
