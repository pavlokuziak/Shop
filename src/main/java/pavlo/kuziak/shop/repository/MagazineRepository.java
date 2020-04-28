package pavlo.kuziak.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pavlo.kuziak.shop.entity.Magazine;

@Repository
public interface MagazineRepository extends JpaRepository<Magazine,Long> {
}
