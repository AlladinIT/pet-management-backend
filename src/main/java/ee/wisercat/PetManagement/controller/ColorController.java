package ee.wisercat.PetManagement.controller;


import ee.wisercat.PetManagement.dto.Color;
import ee.wisercat.PetManagement.service.ColorService;
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
public class ColorController {

    private static final Logger log = LogManager.getLogger(ColorController.class);
    private final ColorService colorService;

    /**
     * This method lists all Colors that are saved in a database.
     *
     * @return List of Color classes or Collections.emptyList().
     */
    @GetMapping("color")
    public List<Color> getAllColors() {
        try {
            return colorService.getAllColors();
        } catch (Exception e) {
            log.error("Error while listing Colors from database: " + e.getMessage());
        }
        return Collections.emptyList();
    }
}
