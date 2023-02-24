package guru.springframework.repositories;

import guru.springframework.domain.Category;
import guru.springframework.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    /*
     JPA will create the required SQL implementation for Us.
     All we need it to add method in interface
   */
    Optional<Category> findByDescription(String description);

    @Override
    Iterable<Recipe> findAll();

    @Override
    <S extends Recipe> S save(S entity);
}
