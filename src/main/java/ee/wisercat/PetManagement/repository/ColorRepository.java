package ee.wisercat.PetManagement.repository;


import ee.wisercat.PetManagement.dto.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {
}
