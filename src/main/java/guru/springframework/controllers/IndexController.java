package guru.springframework.controllers;

import guru.springframework.domain.Category;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.service.RecipeService;
import guru.springframework.service.RecipeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/*
    Tells Spring that this is a Bean

    We constructor inject the repositories
    and then use the method reposirory.findByDescription(long)
    to retrieve the Id of that item from the database
 */
@Slf4j
@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    private RecipeService recipeService;

    public IndexController(CategoryRepository categoryRepository,
                           UnitOfMeasureRepository unitOfMeasureRepository,
                           RecipeServiceImpl recipeService
                           ) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeService = recipeService;
    }

    /*
        @RequestMapping({"","/","/index"}) is the URL path to the index.html page
       return "index" name must match the actual htm file name
     */
    @RequestMapping({"","/","/index"})
    public String getIndexPage(Model model){
        log.debug("Getting index page");
        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        System.out.println("Cat Id is: " + categoryOptional.get().getId());
        System.out.println("UOM ID is: " + unitOfMeasureOptional.get().getId());
        System.out.print("List of recipe ids ");
       recipeService.getRecipes().forEach(r -> System.out.print(r.getId() + ","));
        model.addAttribute("recipes",recipeService.getRecipes());

        return "index";

    }


}
