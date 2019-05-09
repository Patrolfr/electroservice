package komo.fraczek.electronicsservice.repository;



import komo.fraczek.electronicsservice.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

//    Optional<Category> findById(Long id);

    Optional<Category> findByName(String name);

}
