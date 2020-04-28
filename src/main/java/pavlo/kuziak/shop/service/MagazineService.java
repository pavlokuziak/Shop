package pavlo.kuziak.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import pavlo.kuziak.shop.dto.request.MagazineRequest;
import pavlo.kuziak.shop.dto.request.PaginationRequest;
import pavlo.kuziak.shop.dto.response.MagazineResponse;
import pavlo.kuziak.shop.dto.response.PageResponse;
import pavlo.kuziak.shop.entity.Magazine;
import pavlo.kuziak.shop.repository.MagazineRepository;
import pavlo.kuziak.shop.tool.FileTool;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MagazineService {
    @Autowired
    private MagazineRepository magazineRepository;
    @Autowired
    private AuthorService   authorService;

    @Autowired
    private FileTool fileTool;

    public void save(MagazineRequest request) throws IOException {
        final Magazine  magazine= magazineRequestToMagazine(request, null);
        magazineRepository.save(magazine);
    }

    public void update(MagazineRequest request, Long id) throws IOException {
        Magazine  magazine = magazineRequestToMagazine(request, findOne(id));
        magazineRepository.save(magazine);
    }

    public List<MagazineResponse> findAll() {
        return magazineRepository.findAll().stream().map(MagazineResponse::new).collect(Collectors.toList());
    }

    public void delete(Long id) {
        magazineRepository.delete(findOne(id));
    }

    public Magazine findOne(Long id) {
        return magazineRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Magazine with id " + id + " not exists"));
    }

    private Magazine magazineRequestToMagazine(MagazineRequest request, Magazine    magazine) throws IOException {
        if (magazine == null) {
            magazine = new Magazine();
        }
        if (request.getImageName() != null) {
            magazine.setImageName(fileTool.saveFile(request.getImageName()));
        }
        magazine.setName(request.getName());
        magazine.setYear(request.getYear());
        magazine.setPrice(request.getPrice());
        magazine.setAuthor(authorService.findOne(request.getAuthorId()));

        return magazine;
    }

    public PageResponse<MagazineResponse> findAll (PaginationRequest paginationRequest) {
        final Page<Magazine> page = magazineRepository.findAll(paginationRequest.mapToPageable());
        return new PageResponse<>(page.get().map(MagazineResponse::new).collect(Collectors.toList()),
                page.getTotalElements(), page.getTotalPages());
    }
}
