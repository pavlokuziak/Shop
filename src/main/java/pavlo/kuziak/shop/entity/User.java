package pavlo.kuziak.shop.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer age;

    private Double weight;

    private Integer height;

    private String imageName;

    @OneToMany(mappedBy = "user")
    private List <Book> books = new ArrayList<>();
}
