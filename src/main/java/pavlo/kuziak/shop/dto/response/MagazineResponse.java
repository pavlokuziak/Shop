package pavlo.kuziak.shop.dto.response;

import lombok.Getter;
import lombok.Setter;
import pavlo.kuziak.shop.entity.Magazine;

@Getter
@Setter
public class MagazineResponse {
    private Long id;
    private String name;
    private Integer year;
    private Double  price;
    private String imageName;

    private String  authorFirstName;
    private String  authorLastName;

    public MagazineResponse(Magazine    magazine) {
        id = magazine.getId();
        name = magazine.getName();
        year    =   magazine.getYear();
        price   =   magazine.getPrice();
        imageName = magazine.getImageName();

        if (magazine.getAuthor() != null) {
            authorFirstName =   magazine.getAuthor().getFirstName();
            authorLastName = magazine.getAuthor().getLastName();
        }
    }
}
