package ee.wisercat.PetManagement.repository;


import ee.wisercat.PetManagement.dto.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {

}
