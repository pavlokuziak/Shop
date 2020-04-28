package pavlo.kuziak.shop.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter

public class BookRequest {
    @NotBlank
    private String name;
    @Positive
    private Integer year;
    @NotBlank
    private String   genre;
    @PositiveOrZero
    private Double  price;

    private Long    authorId;

    private String image;
}
