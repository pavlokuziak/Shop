package pavlo.kuziak.shop.dto.response;

import lombok.*;
import pavlo.kuziak.shop.entity.Book;

@Getter
@Setter

public class BookResponse {
    private Long id;
    private String name;
    private Integer year;
    private String genre;
    private Double  price;
    private String imageName;
    private String  authorFirstName;
    private String  authorLastName;

    public BookResponse(Book  book) {
        id = book.getId();
        name = book.getName();
        year    =   book.getYear();
        genre   =   book.getGenre();
        price   =   book.getPrice();
        imageName = book.getImageName();

        if (book.getAuthor() != null) {
            authorFirstName = book.getAuthor().getFirstName();
            authorLastName = book.getAuthor().getLastName();
        }
    }
}
