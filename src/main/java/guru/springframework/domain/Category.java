package guru.springframework.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by jt on 6/13/17.
 */
/*
  A@ata - combines
  @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructok
  So these are creates at compile time by Project Lombol
 */
@Data
/* stop circular reference error from bidirectional relationship */
@EqualsAndHashCode(exclude = {"recipes"})


@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToMany(mappedBy = "categories")

    private Set<Recipe> recipes;



}