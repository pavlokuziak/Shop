package pavlo.kuziak.shop.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
public class MagazineRequest {
    @NotBlank
    private String name;
    @PositiveOrZero
    private Integer year;
    @PositiveOrZero
    private Double  price;
    private Long    authorId;
    private String imageName;

}
