package pavlo.kuziak.shop.dto.response;

import lombok.*;
import pavlo.kuziak.shop.entity.User;

@Getter
@Setter

public class UserResponse {
    private Long id;
    private String name;
    private Integer age;
    private Double weight;
    private Integer height;
    private String countryName;
    private String imageName;

    public UserResponse(User user) {
        id = user.getId();
        name = user.getName();
        age = user.getAge();
        weight = user.getWeight();
        height = user.getHeight();
        imageName = user.getImageName();
    }
}
