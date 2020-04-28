package pavlo.kuziak.shop.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BookSearchRequest {

    private String value;
    private Double minPrice;
    private Double maxPrice;
    private Long authorId;

}
