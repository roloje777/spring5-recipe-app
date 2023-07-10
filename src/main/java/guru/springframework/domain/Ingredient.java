package guru.springframework.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by jt on 6/13/17.
 */
@Getter
@Setter
/* stop circular reference error from bidirectional relationship */
@EqualsAndHashCode(exclude = {"recipe"})

@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private BigDecimal amount;

    /*
   FetchType is EAGER by default.
   This is just a way to show other developers the intent
   Tells Hibernate I want you to get it every time.
 */
    @OneToOne(fetch=FetchType.EAGER)
    private UnitOfMeasure uom;


    @ManyToOne
    private Recipe recipe;





}
