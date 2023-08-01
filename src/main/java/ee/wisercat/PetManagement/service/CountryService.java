package ee.wisercat.PetManagement.service;


import ee.wisercat.PetManagement.dto.Country;
import ee.wisercat.PetManagement.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;

    /**
     * This method lists all Countries that are saved in a database.
     *
     * @return List of Countries classes or null.
     */
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }
}
