package mx.arkn37.meli.repository;

import mx.arkn37.meli.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, Long> {

    @Query("SELECT e FROM #{#entityName} e WHERE e.deleteAt IS NULL")
    List<T> findAllActive();

    @Query("SELECT e FROM #{#entityName} e WHERE e.id = ?1 AND e.deleteAt IS NULL")
    Optional<T> findActiveById(Long id);

}
