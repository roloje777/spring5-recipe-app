package guru.springframework.service;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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
        // here we initialize the testy with the mock repository - see @Mock above
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    void getRecipes() throws Exception{
        Recipe recipe = new Recipe();
        HashSet recipesData = new HashSet();

        // should fail as the mock should return an empty set
        //assertEquals(recipesData.size(), 0); // passes
       // assertEquals(recipesData.size(), 1); // failsc

        recipesData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipesData);

        List<Recipe> recipes = recipeService.getRecipes();

        /*
. Just to recap what's going on there. We

created on line 35 to 46 we set up the recipe data and then on 42 this is where

we're telling Mockito. We're saying when when the recipeServic.getRecipes

is called then return back recipesData. So we want our data

returned to the test.

         */

        /*
        what we're saying here verify that the recipeRepository times once and

    we're saying the method findAll(). So we're saying we wanna make sure that

    findAll() is called once and only once, not twice, not zero, but once and so we're

    gonna verify that the recipeRepository was called like that.

         */
       verify(recipeRepository, times(1)).findAll();

        /*

        Here we use mockito to verify if the findAll(0 was called only once..
        If we changed tw 2 the verify should fail

        * */

        verify(recipeRepository, never()).findById(anyLong());


    }

    public void getRecipeByIdTest() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findById(1L);
 // from Q@A I solved the problem by assertNotNull(recipeFoundById, "Null Recipe Returned");
        assertNotNull( recipeReturned, "Null recipe returned");
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }
}