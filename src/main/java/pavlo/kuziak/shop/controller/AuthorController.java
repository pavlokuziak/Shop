package pavlo.kuziak.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pavlo.kuziak.shop.dto.request.AuthorRequest;
import pavlo.kuziak.shop.dto.request.BookRequest;
import pavlo.kuziak.shop.dto.request.PaginationRequest;
import pavlo.kuziak.shop.dto.response.AuthorResponse;
import pavlo.kuziak.shop.dto.response.BookResponseWithAuthor;
import pavlo.kuziak.shop.dto.response.PageResponse;
import pavlo.kuziak.shop.service.AuthorService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @PostMapping
    public void save(@RequestBody AuthorRequest request) throws IOException {
        authorService.save(request);
    }

    @GetMapping
    public List<AuthorResponse> findAll() {
        return authorService.findAll();
    }

//    @GetMapping("/{id}")
//    public AuthorResponse findOne (@PathVariable Long id) {
//
//        //return authorService.findOne(id);
//    }

    @PostMapping("/page")
    public PageResponse<AuthorResponse> findAll(@RequestBody @Valid PaginationRequest paginationRequest) {
        return authorService.findAll(paginationRequest);
    }
    @PutMapping
    public void update(Long id, @RequestBody @Valid AuthorRequest request) throws IOException {
        authorService.update(request, id);
    }

    @DeleteMapping
    public void delete(Long id) {
        authorService.delete(id);
    }
}
