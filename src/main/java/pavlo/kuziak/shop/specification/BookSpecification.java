package pavlo.kuziak.shop.specification;

import org.springframework.data.jpa.domain.Specification;
import pavlo.kuziak.shop.dto.request.BookSearchRequest;
import pavlo.kuziak.shop.entity.Author;
import pavlo.kuziak.shop.entity.Book;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class BookSpecification implements Specification<Book> {
    private String value;
    private Double minPrice;
    private Double maxPrice;
    private Long authorId;

    public BookSpecification(BookSearchRequest bookSearchRequest) {
        value = bookSearchRequest.getValue();
        minPrice = bookSearchRequest.getMinPrice();
        maxPrice = bookSearchRequest.getMaxPrice();
        authorId = bookSearchRequest.getAuthorId();
    }

    @Override
    public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        final List<Predicate> predicates = new ArrayList<>();
        predicates.add(findByNameLike(root, criteriaBuilder));
        predicates.add(findByPrice(root, criteriaBuilder));
        predicates.add(findById(root,criteriaBuilder));
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private Predicate findByNameLike (Root<Book> root, CriteriaBuilder cb) {
        if(value != null && !value.trim().isEmpty()) {
            return cb.like(root.get("name"), '%' + value + '%');
        } else {
            return cb.conjunction();
        }
    }

    private Predicate findByPrice(Root<Book> root, CriteriaBuilder cb) {
        Predicate predicate;

        if(minPrice != null && maxPrice != null) {
            predicate = cb.between(root.get("price"), minPrice, maxPrice);
        } else if (maxPrice != null) {
            predicate = cb.lessThan(root.get("price"), maxPrice);
        } else if (minPrice != null) {
            predicate = cb.lessThan(root.get("price"), minPrice);
        } else {
            predicate = cb.conjunction();
        }

        return predicate;
    }

    private Predicate findById (Root<Book> root, CriteriaBuilder cb) {
        Predicate predicate;
        if(authorId != null) {
            final Join<Book, Author> authorTable = root.join("author");
            predicate = cb.equal(authorTable.get("id"), authorId);
        } else {
            predicate = cb.conjunction();
        }
        return predicate;
    }
 }
