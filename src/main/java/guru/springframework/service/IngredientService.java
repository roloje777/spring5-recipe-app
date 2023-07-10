package guru.springframework.service;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Recipe;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
    IngredientCommand saveIngredientCommand(IngredientCommand command);

    void deleteById(Long recipeId, Long id );
}
