package lol.hugoqdesh.kmdb.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Genre name must not be blank")
    @Size(min = 2, max = 50, message = "Genre name must be between 2 and 50 characters")
    private String name;

    @ManyToMany(mappedBy = "genres")
    private Set<Movie> movies =  new HashSet<>();
}
