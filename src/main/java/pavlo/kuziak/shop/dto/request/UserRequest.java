package pavlo.kuziak.shop.dto.request;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter

public class UserRequest {
    @NotBlank
    private String name;
    @PositiveOrZero
    private Integer age;
    @Positive
    private Double weight;
    @Min(40)
    @Max(300)
    private Integer height;
    @NotNull
    private Long countryId;
    private Long passportId;
    private String image;
}
