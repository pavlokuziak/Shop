package pavlo.kuziak.shop.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter

public class AuthorRequest {

    private String firstName;
    private String lastName;
    private Integer age;
    private String imageName;
}
