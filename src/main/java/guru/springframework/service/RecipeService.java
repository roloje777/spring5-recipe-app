package guru.springframework.service;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;

import java.util.List;

public interface RecipeService {

    public List<Recipe> getRecipes();
    Recipe findById(Long l);

    RecipeCommand findCommandById(Long l);

   RecipeCommand saveRecipeCommand(RecipeCommand command);

   void deleteById(Long idToDelete);
}
