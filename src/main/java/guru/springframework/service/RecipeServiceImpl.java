package guru.springframework.service;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
• @Log - Creates a Java util logger - Java util loggers are awful
• @Slf4j - Creates a
 */
@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> getRecipes(){
        log.debug("I'm in the service");
        List<Recipe> list = new ArrayList<>();
        recipeRepository.findAll().forEach(list::add);
        return list;
    }
}
