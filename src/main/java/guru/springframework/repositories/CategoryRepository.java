package guru.springframework.repositories;

import guru.springframework.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    /*
       JPA will create the required SQL implementation for Us.
       All we need it to add method in interface
     */
    Optional<Category> findByDescription(String description);
}
