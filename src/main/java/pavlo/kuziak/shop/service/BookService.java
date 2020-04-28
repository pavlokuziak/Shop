package pavlo.kuziak.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import pavlo.kuziak.shop.dto.request.BookRequest;
import pavlo.kuziak.shop.dto.request.BookSearchRequest;
import pavlo.kuziak.shop.dto.request.PaginationRequest;
import pavlo.kuziak.shop.dto.response.BookResponse;
import pavlo.kuziak.shop.dto.response.BookResponseWithAuthor;
import pavlo.kuziak.shop.dto.response.PageResponse;
import pavlo.kuziak.shop.entity.Book;
import pavlo.kuziak.shop.repository.BookRepository;
import pavlo.kuziak.shop.specification.BookSpecification;
import pavlo.kuziak.shop.tool.FileTool;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorService   authorService;
    @Autowired
    private FileTool fileTool;

    public void save(BookRequest request) throws IOException {
        final Book book= bookRequestToBook(request, null);
        bookRepository.save(book);
    }

    public void update(BookRequest request, Long id) throws IOException {
        Book book = bookRequestToBook(request, findOne(id));
        bookRepository.save(book);
    }

    public List<BookResponse> findAll() {
        return bookRepository.findAll().stream().map(BookResponse::new).collect(Collectors.toList());
    }

    public void delete(Long id) {
        bookRepository.delete(findOne(id));
    }

    public PageResponse<BookResponse> findAll (PaginationRequest paginationRequest) {
        final Page<Book> page = bookRepository.findAll(paginationRequest.mapToPageable());
        return new PageResponse<>(page.get().map(BookResponse::new).collect(Collectors.toList()),
                page.getTotalElements(), page.getTotalPages());
    }

    public Book findOne(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Book with id " + id
                + " not exists"));
    }

    public BookResponseWithAuthor findOneWithAuthor(Long id) {

        return new BookResponseWithAuthor(findOne(id));
    }

    public List<BookResponse> findAll(BookSearchRequest request) {
        return bookRepository.findAll(new BookSpecification(request))
                .stream().map(BookResponse :: new).collect(Collectors.toList());
    }

    private Book bookRequestToBook(BookRequest request, Book book) throws IOException {
        if (book == null) {
            book = new Book();
        }
        if (request.getImage() != null) {
            book.setImageName(fileTool.saveFile(request.getImage()));
        }
        book.setName(request.getName());
        book.setYear(request.getYear());
        book.setGenre(request.getGenre());
        book.setPrice(request.getPrice());
        book.setAuthor(authorService.findOne(request.getAuthorId()));

        return book;
    }
}
