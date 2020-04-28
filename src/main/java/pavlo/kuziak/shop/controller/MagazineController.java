package pavlo.kuziak.shop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pavlo.kuziak.shop.dto.request.MagazineRequest;
import pavlo.kuziak.shop.dto.request.PaginationRequest;
import pavlo.kuziak.shop.dto.response.MagazineResponse;
import pavlo.kuziak.shop.dto.response.PageResponse;
import pavlo.kuziak.shop.service.MagazineService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/magazine")
public class MagazineController {
    @Autowired
    private MagazineService magazineService;

    @PostMapping
    public void save(@RequestBody @Valid MagazineRequest request) throws IOException {
        magazineService.save(request);
    }

    @GetMapping
    public List<MagazineResponse> findAll() {
        return magazineService.findAll();
    }

    @PutMapping
    public void update(Long id, @RequestBody @Valid MagazineRequest request) throws IOException {
        magazineService.update(request, id);
    }

    @DeleteMapping
    public void delete(Long id) {
        magazineService.delete(id);
    }

    @PostMapping("/page")
    public PageResponse<MagazineResponse> findAll(@RequestBody @Valid PaginationRequest paginationRequest) {
        return magazineService.findAll(paginationRequest);
    }
}
