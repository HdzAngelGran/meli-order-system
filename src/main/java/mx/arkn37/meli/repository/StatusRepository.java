package mx.arkn37.meli.repository;

import mx.arkn37.meli.model.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepository extends BaseRepository<Status> {

    @Query("SELECT o FROM Status o WHERE o.code = ?1 AND o.deletedAt IS NULL")
    Optional<Status> findActiveByCode(String code);

}
