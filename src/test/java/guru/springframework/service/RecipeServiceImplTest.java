package guru.springframework.service;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    //initialize the mocks
    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    void getRecipes() throws Exception{
        Recipe recipe = new Recipe();
        HashSet recipesData = new HashSet();
        recipesData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipesData);

        List<Recipe> recipes = recipeService.getRecipes();

        assertEquals(recipes.size(), 1);
        /*
        what we're saying here verify that the recipeRepository times once and

    we're saying the method findAll(). So we're saying we wanna make sure that

    findAll() is called once and only once, not twice, not zero, but once and so we're

    gonna verify that the recipeRepository was called like that.

         */
       verify(recipeRepository, times(1)).findAll();

        /*
        We can see that we're still passing now. If I were to change this to say three,

        we'll see a failure.

        Now you can see that down in the window that we're saying wanted three

    times but only one was called one time. So it's a good way to verify that

    your interactions within the class are happening as expected.

      verify(recipeRepository, times(3)).findAll();
         */



    }
}