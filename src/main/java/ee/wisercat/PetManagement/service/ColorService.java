package ee.wisercat.PetManagement.service;


import ee.wisercat.PetManagement.dto.Color;
import ee.wisercat.PetManagement.repository.ColorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ColorService {

    private final ColorRepository colorRepository;

    /**
     * This method lists all Colors that are saved in a database.
     *
     * @return List of Colors classes or null.
     */
    public List<Color> getAllColors() {
        return colorRepository.findAll();
    }

}
