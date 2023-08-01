package ee.wisercat.PetManagement.controller;

import ee.wisercat.PetManagement.dto.LoginRequest;
import ee.wisercat.PetManagement.dto.LoginResponse;
import ee.wisercat.PetManagement.dto.PersonRequest;
import ee.wisercat.PetManagement.dto.PersonResponse;
import ee.wisercat.PetManagement.service.PersonService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping(value = "api/", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200")
public class PersonController {

    private static final Logger log = LogManager.getLogger(PersonController.class);
    private final PersonService personService;

    /**
     * This method lists all Person that are saved in a database.
     *
     * @return List of PersonResponse classes or Collections.emptyList().
     */
    @GetMapping("person")
    public List<PersonResponse> getAllPersons() {
        try {
            return personService.getAllPersons();
        } catch (Exception e) {
            log.error("Error while listing People from database: " + e.getMessage());
        }
        return Collections.emptyList();
    }


    /**
     * This method saves new Person(user) in a database (Registration).
     *
     * @param personRequest new Person(user).
     * @return ResponseEntity class or null.
     */
    @PostMapping("person")
    public ResponseEntity<PersonResponse> addNewPerson(@RequestBody PersonRequest personRequest, HttpServletRequest request) {
        try {

            PersonResponse addedPerson = personService.addNewPerson(personRequest);
            if (addedPerson != null) {
                request.getSession().setAttribute("userId", addedPerson.getUserId());
            }
            return ResponseEntity.ok(addedPerson);
        } catch (Exception e) {
            log.error("Error while adding new Person to database: " + e.getMessage());
        }
        return null;
    }


    /**
     * This method updates Person in a database.
     *
     * @param personRequest updated Person
     * @param personId      ID of person(user) you want to update
     * @return ResponseEntity class or null.
     */
    @PutMapping("person/{id}")
    public ResponseEntity<PersonResponse> updatePerson(@RequestBody PersonRequest personRequest,
                                                       @PathVariable("id") Long personId) {
        try {
            PersonResponse updatedPerson = personService.updatePerson(personRequest, personId);
            return ResponseEntity.ok(updatedPerson);
        } catch (Exception e) {
            log.error("Error while updating Person in database: " + e.getMessage());
        }
        return null;
    }


    /**
     * This method deletes Person from a database.
     *
     * @param personId ID of the person to delete
     */
    @DeleteMapping("person/{id}")
    public void deletePerson(@PathVariable("id") Long personId) {
        try {
            personService.deletePerson(personId);
        } catch (Exception e) {
            log.error("Error while deleting Person from database: " + e.getMessage());
        }
    }


    /**
     * This method makes a login to the user account and creates a session
     *
     * @param loginRequest that contains name and password of the user
     * @param request      HttpServletRequest
     * @return ResponseEntity class or null.
     */
    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        try {
            LoginResponse loginResponse = personService.loginUser(loginRequest);

            if (loginResponse.getStatus() == true) {
                request.getSession().setAttribute("userId", loginResponse.getUserId());
            }

            return ResponseEntity.ok(loginResponse);
        } catch (Exception e) {
            log.error("Error while login: " + e.getMessage());
        }
        return null;
    }


    /**
     * This method makes a logout from user account and deletes session
     *
     * @param request HttpServletRequest
     */
    @PostMapping("logout")
    public void logout(HttpServletRequest request) {
        try {
            Long userId = (Long) request.getSession().getAttribute("userId");
            if (userId != null) {
                request.getSession().invalidate();
            }

        } catch (Exception e) {
            log.error("Error while logout: " + e.getMessage());
        }
    }


    /**
     * This method checks is user is logged in or not
     *
     * @param request HttpServletRequest
     * @return True or False or Null(if error)
     */
    @GetMapping("check-login")
    public Boolean isUserLoggedIn(HttpServletRequest request) {
        try {
            Long userId = (Long) request.getSession().getAttribute("userId");
            if (userId != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            log.error("Error while checking if a user is logged in: " + e.getMessage());
        }
        return null;
    }


}
