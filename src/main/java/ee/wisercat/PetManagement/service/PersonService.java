package ee.wisercat.PetManagement.service;


import ee.wisercat.PetManagement.dto.*;
import ee.wisercat.PetManagement.exception.PersonIsNotValidException;
import ee.wisercat.PetManagement.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    private final PasswordEncoder passwordEncoder;

    /**
     * This method lists all Person that are saved in a database.
     *
     * @return List of PersonResponse classes or null.
     */
    public List<PersonResponse> getAllPersons() {
        List<Person> persons = personRepository.findAll();
        List<PersonResponse> personsResponsesList = new ArrayList<>();
        for (Person p : persons) {
            personsResponsesList.add(new PersonResponse(p.getName(), p.getId()));
        }

        return personsResponsesList;
    }


    /**
     * This method saves new Person(user) in a database (Registration).
     *
     * @param personRequest new Person(user).
     * @return PersonResponse class or null.
     * @throws PersonIsNotValidException
     */
    public PersonResponse addNewPerson(PersonRequest personRequest) throws PersonIsNotValidException {

        if (inputIsNull(personRequest)) {
            throw new PersonIsNotValidException("Input parameters are invalid");
        }

        if (nameAlreadyExists(personRequest)) {
            throw new PersonIsNotValidException("Name should be unique");
        }

        Person person = new Person();
        person.setName(personRequest.getName());
        person.setPassword(passwordEncoder.encode(personRequest.getPassword()));

        personRepository.save(person);
        return new PersonResponse(person.getName(), person.getId());
    }


    /**
     * This method updates Person in a database.
     *
     * @param personRequest updated Person
     * @param personId      ID of person(user) you want to update
     * @return PersonResponse class or null.
     * @throws PersonIsNotValidException
     */
    public PersonResponse updatePerson(PersonRequest personRequest, Long personId) throws PersonIsNotValidException {

        if (inputIsNull(personRequest)) {
            throw new PersonIsNotValidException("Input parameters are invalid");
        }

        Person savedPerson = personRepository.findById(personId).orElseThrow(
                () -> new EntityNotFoundException("You don't have Person with id: " + personId + " saved in database.")
        );

        if (nameAlreadyExists(personRequest, personId)) {
            throw new PersonIsNotValidException("Name should be unique");
        }

        savedPerson.setName(personRequest.getName());
        savedPerson.setPassword(passwordEncoder.encode(personRequest.getPassword()));

        personRepository.save(savedPerson);
        return new PersonResponse(savedPerson.getName(), savedPerson.getId());
    }


    /**
     * This method deletes Person from a database.
     *
     * @param personId ID of the person to delete
     */
    public void deletePerson(Long personId) {

        personRepository.findById(personId).orElseThrow(
                () -> new EntityNotFoundException("You don't have Person with id: " + personId + " saved in database.")
        );

        personRepository.deleteById(personId);

    }


    private boolean inputIsNull(PersonRequest personRequest) {
        if (personRequest.getName() == null || personRequest.getPassword() == null) {
            return true;
        }
        return false;
    }

    //For UPDATING EXISTING USER
    private boolean nameAlreadyExists(PersonRequest personRequest, Long personId) {
        Person alreadySavedPerson = personRepository.findByName(personRequest.getName());
        if (alreadySavedPerson != null && !alreadySavedPerson.getId().equals(personId)) {
            return true;
        }
        return false;
    }

    //For ADDING NEW user
    private boolean nameAlreadyExists(PersonRequest personRequest) {
        Person alreadySavedPerson = personRepository.findByName(personRequest.getName());
        if (alreadySavedPerson != null) {
            return true;
        }
        return false;
    }


    /**
     * This method makes a login to the user account and creates a session
     *
     * @param loginRequest that contains name and password of the user
     * @return LoginResponse class.
     */
    public LoginResponse loginUser(LoginRequest loginRequest) {
        Person person = personRepository.findByName(loginRequest.getName());
        if (person != null) {
            String password = loginRequest.getPassword();
            String encodedPassword = person.getPassword();
            boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                return new LoginResponse("Login successful", true, person.getId());
            } else {
                return new LoginResponse("Password is incorrect", false, null);
            }
        } else {
            return new LoginResponse("No such user", false, null);
        }
    }
}
