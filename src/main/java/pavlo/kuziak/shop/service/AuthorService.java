package pavlo.kuziak.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import pavlo.kuziak.shop.dto.request.AuthorRequest;
import pavlo.kuziak.shop.dto.request.BookRequest;
import pavlo.kuziak.shop.dto.request.PaginationRequest;
import pavlo.kuziak.shop.dto.response.AuthorResponse;
import pavlo.kuziak.shop.dto.response.PageResponse;
import pavlo.kuziak.shop.entity.Author;
import pavlo.kuziak.shop.entity.Book;
import pavlo.kuziak.shop.repository.AuthorRepository;
import pavlo.kuziak.shop.tool.FileTool;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository    authorRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private FileTool fileTool;

    public void save(AuthorRequest request) throws IOException {
       final Author author = authorRequestToAuthor(request, null);
        authorRepository.save(author);
    }

    public List<AuthorResponse> findAll() {
        return authorRepository.findAll()
                .stream()
                .map(AuthorResponse::new)
                .collect(Collectors.toList());

    }

    public Author findOne(Long id) {
        return authorRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Author with id " + id + " not exists"));
    }

    public void delete(Long id) {
       authorRepository.delete(findOne(id));
    }

    public PageResponse<AuthorResponse> findAll (PaginationRequest paginationRequest) {
        final Page<Author> page = authorRepository.findAll(paginationRequest.mapToPageable());
        return new PageResponse<>(page.get().map(AuthorResponse::new).collect(Collectors.toList()),
                page.getTotalElements(), page.getTotalPages());
    }

    public void update(AuthorRequest request, Long id) throws IOException {
        Author author = authorRequestToAuthor(request, findOne(id));
        authorRepository.save(author);
    }

    private Author authorRequestToAuthor(AuthorRequest request, Author author) throws IOException {
        if (author == null) {
            author = new Author();
        }
        if (request.getImageName() != null) {
            author.setImageName(fileTool.saveFile(request.getImageName()));
        }
        author.setFirstName(request.getFirstName());
        author.setLastName(request.getLastName());
        author.setAge(request.getAge());

        return author;
    }

}
