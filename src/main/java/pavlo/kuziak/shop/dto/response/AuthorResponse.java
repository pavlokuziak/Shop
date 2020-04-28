package pavlo.kuziak.shop.dto.response;

import lombok.*;
import pavlo.kuziak.shop.entity.Author;

@Getter
@Setter

public class AuthorResponse {
    private Long id;
    private String  firstName;
    private String lastName;
    private Integer  age;
    private String imageName;

    public AuthorResponse(Author author) {
        id = author.getId();
        firstName=author.getFirstName();
        lastName = author.getLastName() ;
        age=author.getAge();
        imageName = author.getImageName();
    }
}
