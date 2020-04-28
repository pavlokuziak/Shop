package pavlo.kuziak.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pavlo.kuziak.shop.dto.request.BookRequest;
import pavlo.kuziak.shop.dto.request.BookSearchRequest;
import pavlo.kuziak.shop.dto.request.PaginationRequest;
import pavlo.kuziak.shop.dto.response.BookResponse;
import pavlo.kuziak.shop.dto.response.BookResponseWithAuthor;
import pavlo.kuziak.shop.dto.response.PageResponse;
import pavlo.kuziak.shop.service.BookService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public void save(@RequestBody @Valid BookRequest request) throws IOException {
        bookService.save(request);
    }

    @GetMapping
    public List<BookResponse> findAll() {
        return bookService.findAll();
    }

    @GetMapping("/filter")
    public List<BookResponse> findAll(@Valid BookSearchRequest bookSearchRequest) {

        return bookService.findAll(bookSearchRequest);
    }

    @GetMapping("/{id}")
    public BookResponseWithAuthor findOne (@PathVariable Long id) {

        return bookService.findOneWithAuthor(id);
    }

    @PostMapping("/page")
    public PageResponse<BookResponse> findAll(@RequestBody @Valid PaginationRequest paginationRequest) {
        return bookService.findAll(paginationRequest);
    }

    @PutMapping
    public void update(Long id, @RequestBody @Valid BookRequest request) throws IOException {
        bookService.update(request, id);
    }

    @DeleteMapping
    public void delete(Long id) {
        bookService.delete(id);
    }



}
