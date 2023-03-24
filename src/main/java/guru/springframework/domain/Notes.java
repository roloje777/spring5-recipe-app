package guru.springframework.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by jt on 6/13/17.
 */

/*
annotate the

class as an Entity. So this is now creating this class as an Entity
 */

    @Data
    /* stop circular reference error from bidirectional relationship */
    @EqualsAndHashCode(exclude = {"recipe"})

@Entity
public class Notes {

    /*
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

    /*
    So I'm going to come down

to this field here and add in the one-to-one mapping. So that is going to

create the relationship for the one-to-one mapping.
     */
    @OneToOne
    private Recipe recipe;
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
    @Lob
    private String recipeNotes;


}
