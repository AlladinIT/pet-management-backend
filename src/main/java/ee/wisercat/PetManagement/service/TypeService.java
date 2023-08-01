package ee.wisercat.PetManagement.service;


import ee.wisercat.PetManagement.dto.Type;
import ee.wisercat.PetManagement.repository.TypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TypeService {

    private final TypeRepository typeRepository;

    /**
     * This method lists all Types that are saved in a database.
     *
     * @return List of Types classes or null.
     */
    public List<Type> getAllTypes() {
        return typeRepository.findAll();
    }
}
