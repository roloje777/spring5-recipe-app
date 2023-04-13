package guru.springframework.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jt on 6/13/17.
 */

/*  A@ata - combines
  @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructok
  So these are creates at compile time by Project Lombok
  Just two left as they are tailor made (stNotes and AddIngredients
 */
/*
annotate the class as an Entity. So this is now creating this class as an Entity
 */


@Data
/* stop circular reference error from bidirectional relationship */
@EqualsAndHashCode(exclude = {"notes", "ingredient"})

@Entity
public class Recipe {

    /*c
 give it an ID value. So under the covers, objects are

going to get their own ID value within the JVM but we're persisting it down to

a relational database which it really doesn't have a concept of an ID. So I'm

going to add in a property for the ID value.

So that creates that and we're going to annotate that with the @Id annotation

and that is a standard javax persistence. Now we want to say how to

generate it and in this case we are going to rely on an auto generating

field. So we're going to give it a strategy and we're going to say IDENTITY

and that is going to leverage the underlying persistence framework to

generate an ID value for us. Now I do want to point out that in using the

generation type of IDENTITY, that is a special type to relational databases

that will support the automatic generation of a sequence. So MySQL

does have an auto-generated primary key property that we can use as well as

several other databases do.

  */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;

    // so it allows large strings
    @Lob
    private String directions;
    //todo add
    //private Difficulty difficulty;


    /*
Now there is a special use case that we want to look at and we have two

of them there and we have an annotation called @Lob. I've not talked about this

before but this is for large objects and we, in a relational database you can use

CLOBS for character large objects or BLOBS for binary large objects. So in

this case by default a String in Java can be crazy large, I forget the exact

limitation of it off the top of my head but a lot larger than the default of

hibernate and JPA which is going to be 255 characters. So we want to allow our

users to put in a lot more than 250 characters or 255 characters on the

Notes field. So what we are going to do to use the Lob annotation. And by doing

this against a String, JPA is going to expect a distort in a CLOB field in

the database.
 */

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>(); // added so that I don't have to create object outside class
    @Lob
    private Byte[] image;


    @Enumerated(value=EnumType.ORDINAL)
    private Difficulty difficulty;

    /*
        So I'm going to come down

to this field here and add in the one-to-one mapping. So that is going to

create the relationship for the one-to-one mapping and I also want to

set up a cascade. So you can make the recipe the owner of that and we're going

to do cascade all.
     */
    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    @ManyToMany
    @JoinTable(name = "recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;



    public void setNotes(Notes notes) {

        this.notes = notes;
        notes.setRecipe(this); // added for bi-directional update from one location
    }

    /*
       Utility method added so One can add ingredients from the recipe jojo
       instead in the ingriendient pojo
     */
    public Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }


}