package ee.wisercat.PetManagement.controller;


import ee.wisercat.PetManagement.dto.PetRequest;
import ee.wisercat.PetManagement.dto.PetResponse;
import ee.wisercat.PetManagement.service.PetService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "api/", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200")
public class PetController {

    private static final Logger log = LogManager.getLogger(PetController.class);
    private final PetService petService;

    /**
     * This method lists all Pets for a logged-in user
     *
     * @param request HttpServletRequest
     * @return List of PetResponse classes or null.
     */
    @GetMapping("pet")
    public List<PetResponse> getAllPets(HttpServletRequest request) {
        try {
            Long userId = (Long) request.getSession().getAttribute("userId");
            return petService.getAllPets(userId);
        } catch (Exception e) {
            log.error("Error while listing Pets from database: " + e.getMessage());
        }
        return null;
    }


    /**
     * This method saves new Pet in a database.
     *
     * @param petRequest new pet.
     * @param request    HttpServletRequest
     * @return PetResponse class or null.
     */
    @PostMapping("pet")
    public PetResponse addNewPet(@RequestBody PetRequest petRequest, HttpServletRequest request) {
        try {
            Long userId = (Long) request.getSession().getAttribute("userId");
            return petService.addNewPet(petRequest, userId);
        } catch (Exception e) {
            log.error("Error while adding new Pet to database: " + e.getMessage());
        }
        return null;
    }


    /**
     * This method updates Pet in a database.
     *
     * @param petRequest updated Pet
     * @param petId      ID (unique identification number) of pet  you want to update
     * @param request    HttpServletRequest
     * @return Pet class or null.
     */
    @PutMapping("pet/{id}")
    public PetResponse updatePet(@RequestBody PetRequest petRequest, @PathVariable("id") Long petId, HttpServletRequest request) {
        try {
            Long userId = (Long) request.getSession().getAttribute("userId");
            return petService.updatePet(petRequest, petId, userId);

        } catch (Exception e) {
            log.error("Error while updating Pet in database: " + e.getMessage());
        }
        return null;
    }


    /**
     * This method deletes Pet from a database.
     *
     * @param petId   ID (unique identification number) of the pet to delete
     * @param request HttpServletRequest
     */
    @DeleteMapping("pet/{id}")
    public void deletePet(@PathVariable("id") Long petId, HttpServletRequest request) {
        try {
            Long userId = (Long) request.getSession().getAttribute("userId");
            petService.deletePet(petId, userId);
        } catch (Exception e) {
            log.error("Error while deleting Pet from database: " + e.getMessage());
        }
    }

}

