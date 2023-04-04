package guru.springframework.controllers;

import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.service.RecipeService;
import guru.springframework.service.RecipeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import static org.mockito.Mockito.*; // needed for the verify method

import static org.junit.jupiter.api.Assertions.*;

class IndexControllerTest {

    @Mock
    RecipeService recipeService;
    @Mock
    Model model;

    IndexController controller;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
//        recipeService = Mockito.mock(RecipeService.class);
//        categoryRepository = Mockito.mock();

        controller = new IndexController(recipeService);
    }

    @Test
    public void getIndexPage(){
        String viewName = controller.getIndexPage(model);

        assertEquals("index", viewName);
        verify(recipeService, times(1)).getRecipes();
        verify(model, times(1)).addAttribute(eq("recipes"), anyList());
    }
}