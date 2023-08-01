package ee.wisercat.PetManagement.controller;


import ee.wisercat.PetManagement.dto.Type;
import ee.wisercat.PetManagement.service.TypeService;
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
public class TypeController {


    private static final Logger log = LogManager.getLogger(TypeController.class);
    private final TypeService typeService;

    /**
     * This method lists all Types that are saved in a database.
     *
     * @return List of Types classes or Collections.emptyList().
     */
    @GetMapping("type")
    public List<Type> getAllTypes() {
        try {
            return typeService.getAllTypes();
        } catch (Exception e) {
            log.error("Error while listing Types from database: " + e.getMessage());
        }
        return Collections.emptyList();
    }
}
