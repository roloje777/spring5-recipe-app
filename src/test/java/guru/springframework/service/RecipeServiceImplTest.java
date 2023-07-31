package guru.springframework.service;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.exceptions.NotFoundException;
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

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    //initialize the mocks
    @BeforeEach
    public void setUp() throws Exception {
        // here we initialize the testy with the mock repository - see @Mock above
        MockitoAnnotations.initMocks(this);

        //recipeService = new RecipeServiceImpl(recipeRepository);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
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

    @Test
    public void testGetRecipeByIdTestNotFound() throws Exception {
        // given
        Optional<Recipe> recipeOptional = Optional.empty();

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        // when
        NotFoundException notFoundException = assertThrows(
                NotFoundException.class, () -> recipeService.findById(1L),
                "Expected exception to throw an error. But it didn't"
        );

        // then
        assertTrue(notFoundException.getMessage().contains("Recipe Not Found"));
    }

    @Test
    public void getRecipeCoomandByIdTest() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        when(recipeToRecipeCommand.convert(any())).thenReturn(recipeCommand);

        RecipeCommand commandById = recipeService.findCommandById(1L);

        assertNotNull(commandById,"Null recipe returned");
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    public void testDeleteById() throws Exception {

        //given
        Long idToDelete = Long.valueOf(2L);

        //when
        recipeService.deleteById(idToDelete);

        //no 'when', since method has void return type

        //then
        verify(recipeRepository, times(1)).deleteById(anyLong());
    }
}