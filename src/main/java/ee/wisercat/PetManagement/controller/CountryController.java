package ee.wisercat.PetManagement.controller;


import ee.wisercat.PetManagement.dto.Country;
import ee.wisercat.PetManagement.service.CountryService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "api/", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200")
public class CountryController {


    private static final Logger log = LogManager.getLogger(CountryController.class);
    private final CountryService countryService;

    /**
     * This method lists all Countries that are saved in a database.
     *
     * @return List of Countries classes or Collections.emptyList().
     */
    @GetMapping("country")
    public List<Country> getAllCountries() {
        try {
            return countryService.getAllCountries();
        } catch (Exception e) {
            log.error("Error while listing Countries from database: " + e.getMessage());
        }
        return Collections.emptyList();
    }
}
