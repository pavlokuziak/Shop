package pavlo.kuziak.shop.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import pavlo.kuziak.shop.entity.Book;

@Getter
@Setter
public class BookResponseWithAuthor {

    private Long id;
    private String name;
    private Integer year;
    private String genre;
    private Double  price;

    @JsonProperty("author")
    private AuthorResponse  authorResponse;

    public BookResponseWithAuthor(Book  book) {
        id = book.getId();
        name = book.getName();
        year    =   book.getYear();
        genre   =   book.getGenre();
        price   =   book.getPrice();

        if (book.getAuthor() != null) {
            authorResponse = new AuthorResponse(book.getAuthor());
        }
    }
}
