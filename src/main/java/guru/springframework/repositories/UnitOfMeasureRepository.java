package guru.springframework.repositories;

import guru.springframework.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

    /*
   JPA will create the required SQL implementation for Us.
   All we need it to add method in interface
 */
    Optional<UnitOfMeasure> findByDescription(String description);
}
