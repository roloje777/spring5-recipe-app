package guru.springframework.controllers;

import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.service.RecipeService;
import guru.springframework.service.RecipeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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

    RecipeServiceImpl recipeServiceImpl;

    CategoryRepository categoryRepository;

    UnitOfMeasureRepository unitOfMeasureRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        controller = new IndexController(categoryRepository,unitOfMeasureRepository,recipeServiceImpl);
    }

    @Test
    public void getIndexPage(){
        String viewName = controller.getIndexPage(model);

        // assert if the String returned is index
        assertEquals(viewName, "index");

        verify(recipeService,times(1)).getRecipes();
        verify(model,times(1)).addAttribute("recipes",anyList()); //verify if it returns a List
    }
}