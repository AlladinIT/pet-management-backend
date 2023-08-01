package ee.wisercat.PetManagement.repository;

import ee.wisercat.PetManagement.dto.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
