package mx.arkn37.meli.repository;

import mx.arkn37.meli.model.ExposedEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.awt.print.Pageable;
import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public interface ExposedRepository<T extends ExposedEntity> extends BaseRepository<T> {

    Optional<T> findByUuidAndDeleteAtIsNull(UUID uuid);

    Optional<T> findByUuid(UUID uuid);

}
