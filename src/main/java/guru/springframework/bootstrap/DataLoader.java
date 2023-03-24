package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
  Initializes database with data at startup
 */
@Slf4j
@Component
public class DataLoader implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;


    private final RecipeRepository recipeRepository;

    public DataLoader(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        log.debug("Loading bootstap data");
        // Perfect Guacamole
      //  Recipe perfectGuacamole = recipeRepository.save(new Recipe());
       Recipe perfectGuacamole = new Recipe();
        System.out.println(perfectGuacamole.getId());

        perfectGuacamole.setDescription("The best guacamole keeps it simple: just ripe avocados and a handful of flavorful mix-ins.\n" +
                "Serve it as a dip at your next party or spoon it on top of tacos for an easy dinner upgrade.");
        perfectGuacamole.setPrepTime(10);
        perfectGuacamole.setCookTime(0);
        perfectGuacamole.setServings(4);
        perfectGuacamole.setDifficulty(Difficulty.EASY);

        // add categories
        Set<Category> categories = new HashSet<>();
        //categories.add(categoryRepository.findByDescription("Mexican").get());
//       addCategory(perfectGuacamole, categories, "Mexican");
        addCategory(perfectGuacamole, categories, "Fast Food");

        perfectGuacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        perfectGuacamole.setSource("none");


  /*
      // add ingredients
        Set<Ingredient> ingredients = new HashSet<>();
        addIngredient(ingredients,0.25,"kosher salt, plus more to taste","Teaspoon",perfectGuacamole);
        addIngredient(ingredients, 1,"fresh lime or lemon juice","Tablespoon",perfectGuacamole);
        addIngredient(ingredients, 1,"minced red onion or thinly sliced green onion","Tablespoon",perfectGuacamole);
        addIngredient(ingredients, 2,"serrano (or jalapeño) chilis, stems and seeds removed, minced","Each",perfectGuacamole);
        addIngredient(ingredients,2,"cilantro (leaves and tender stems), finely chopped","Tablespoon",perfectGuacamole);
        addIngredient(ingredients,1,"freshly ground black pepper","Pinch",perfectGuacamole);
        addIngredient(ingredients,0.5,"Red radish or jicama slices for garnish (optional)","Each",perfectGuacamole);
        addIngredient(ingredients,1,"Tortila chips","Each",perfectGuacamole);

        perfectGuacamole.setIngredients(ingredients);// add ingredients to recipe

*/
        // refactored to make use the addIngrients untlity method
      addIngredient(0.25,"kosher salt, plus more to taste","Teaspoon",perfectGuacamole);
      addIngredient(1,"fresh lime or lemon juice","Tablespoon",perfectGuacamole);
      addIngredient( 1,"minced red onion or thinly sliced green onion","Tablespoon",perfectGuacamole);
      addIngredient(2,"serrano (or jalapeño) chilis, stems and seeds removed, minced","Each",perfectGuacamole);
        addIngredient(2,"cilantro (leaves and tender stems), finely chopped","Tablespoon",perfectGuacamole);
        addIngredient(1,"freshly ground black pepper","Pinch",perfectGuacamole);
        addIngredient(0.5,"Red radish or jicama slices for garnish (optional)","Each",perfectGuacamole);
        addIngredient(1,"Tortila chips","Each",perfectGuacamole);



        perfectGuacamole.setDirections("Cust avocados.\n Mash avocado flesh.\n ");


      //set Notes
                      Notes notes = new Notes();
                      notes.setRecipeNotes("If making a few hours ahead, place plastic wrap on the surface of the guacamole\n" +
                              "and press down to cover it to prevent air reaching it. (The oxygen in the air causes oxidation\n" +
                              "which will turn the guacamole brown.)" +
                              "\n" +
                              "Garnish with slices of red radish or jigama strips. Serve with your choice of store-bought tortilla chips or make your own homemade tortilla chips." +
                              "\n" +
                              "Refrigerate leftover guacamole up to 3 days." +
                              "\n" +
                              "Note: Chilling tomatoes hurts their flavor. So, if you want to add chopped tomato to your\n" +
                              "guacamole, add it just before serving.");

                      /*
                         setNotes no longer needed to be called

                       */
                    // notes.setRecipe(perfectGuacamole);
                      perfectGuacamole.setNotes(notes);

       //  save to repository
        perfectGuacamole = recipeRepository.save(perfectGuacamole);
        //display results
        display(perfectGuacamole);



        // Spicy grilled chicken
       // Recipe chicken = recipeRepository.save(new Recipe());
        Recipe chicken= new Recipe();

        System.out.println(chicken.getId());

        chicken.setDescription("Spicy Grilled Chicken Tacos\nSpicy grilled chicken tacos! Quick marinade, then grill.\n Ready in about 30 minutes. Great for a quick weeknight dinner, backyard cookouts, and tailgate parties.\n");
        chicken.setPrepTime(20);
        chicken.setCookTime(10);
        chicken.setServings(5);
        chicken.setDifficulty(Difficulty.MODERATE);

        // add categories
        categories = new HashSet<>();
       // categories.add(categoryRepository.findByDescription("Italian").get());
        addCategory(chicken,categories,"Italian");

      chicken.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        chicken.setSource("none");


        // add ingredients
        //ingredients = new HashSet<>();
        addIngredient(2,"ancho chili powder","Tablespoon",chicken);
        addIngredient(1,"dried oregano","Teaspoon",chicken);
        addIngredient(1,"dried cumin","Teaspoon",chicken);
        addIngredient(1,"sugar","Teaspoon",chicken);
        addIngredient(1,"skosher salt","Teaspoon",chicken);
        addIngredient(1,"garlic, finely chopped","Clove",chicken);
        addIngredient(3,"finely grated orange zest","Tablespoon",chicken);
        addIngredient(3,"fresh-squeezed orange juice","Tablespoon",chicken);
        addIngredient(3,"Olive oil","Tablespoon",chicken);
        addIngredient(1.25,"4 to 6 skinless, boneless chicken thighs","Pound",chicken);


        chicken.setDirections("Prepare either a gas or charcoal grill for medium-high, direct heat.");

        //set Notes
        notes = new Notes();
        notes.setRecipeNotes("The ancho chiles I use in the marinade are named for their wide shape.");

        /*
           notes no longer needed to be called
         */
       // notes.setRecipe(chicken);
        chicken.setNotes(notes);

        //  save to repository and read back object
        chicken = recipeRepository.save(chicken);
        //display results
        display(chicken);

        /* debugiong code */



    }

    private void addCategory(Recipe recipe, Set<Category> categories, String description) {

        categoryRepository.findByDescription(description).ifPresent(c -> categories.add(c));
        recipe.setCategories(categories);
    }

    private void display(Recipe recipe) {

        System.out.println(recipe.getId() +  "\n" +
               "Description -\n" + recipe.getDescription()+  " \n" +
                "prep time - " + recipe.getPrepTime()+  " \n" +
               "cook time - " + recipe.getCookTime()+  " \n" +
                "serves = " + recipe.getServings()+  " \n" +
                "source - " + recipe.getSource()+  " \n" +
               "url -" + recipe.getUrl() +  " \n" +
               "Directions -\n"+  recipe.getDirections() +  " \n" +
               "Difficulty is " + recipe.getDifficulty() + "\n" +
                "image - " + recipe.getImage()+  " \n" +
               "notes -\n" + recipe.getNotes().getRecipeNotes()+ "\n" +
               recipe.getCategories().stream().findFirst().get().getDescription()+ "\n" +
                recipe.getIngredients().stream().findFirst().get().getAmount() + " " +
                recipe.getIngredients().stream().findFirst().get().getUom().getDescription() +
               recipe.getIngredients().stream().findFirst().get().getDescription() + "\n"+
               "... saved ...");
    }


    private void addIngredient(double d, String description, String uom, Recipe recipe){
        Ingredient ingredient = new Ingredient();
        ingredient.setAmount(new BigDecimal(d));
        ingredient.setDescription(description);
        //ingredient.setUom(unitOfMeasureRepository.findByDescription(uom).get());
        unitOfMeasureRepository.findByDescription(uom).ifPresent(u -> ingredient.setUom(u));
        recipe.addIngredient(ingredient); // calls utilty method


    }




}
