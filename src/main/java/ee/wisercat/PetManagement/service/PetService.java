package ee.wisercat.PetManagement.service;


import ee.wisercat.PetManagement.dto.*;
import ee.wisercat.PetManagement.exception.PersonIsNotValidException;
import ee.wisercat.PetManagement.exception.PetIsNotValidException;
import ee.wisercat.PetManagement.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PetService {

    private final PersonRepository personRepository;

    private final PetRepository petRepository;

    private final ColorRepository colorRepository;

    private final CountryRepository countryRepository;

    private final TypeRepository typeRepository;


    /**
     * This method lists all Pets for a logged-in user
     *
     * @param userId ID of a user
     * @return List of PetResponse classes or null.
     * @throws PersonIsNotValidException
     */
    public List<PetResponse> getAllPets(Long userId) throws PersonIsNotValidException {
        if (userIsNotLoggedIn(userId)) {
            throw new PersonIsNotValidException("User is not logged-in");
        }

        List<Pet> pets = petRepository.findAllByPersonId(userId);
        List<PetResponse> petsResponsesList = new ArrayList<>();
        for (Pet p : pets) {

            petsResponsesList.add(new PetResponse(
                    p.getId(),
                    p.getName(),
                    p.getCode(),
                    p.getPerson().getName(),
                    p.getType().getType(),
                    p.getColor().getColor(),
                    (p.getCountry() != null ? p.getCountry().getCountry() : null),
                    p.getType().getId(),
                    p.getColor().getId(),
                    (p.getCountry() != null ? p.getCountry().getId() : null))
            );
        }

        return petsResponsesList;

    }


    /**
     * This method saves new Pet in a database.
     *
     * @param petRequest new pet.
     * @param userId     id of a user that pet belongs to
     * @return PetResponse class or null.
     * @throws PersonIsNotValidException
     * @throws PetIsNotValidException
     */
    public PetResponse addNewPet(PetRequest petRequest, Long userId) throws PersonIsNotValidException, PetIsNotValidException {
        if (userIsNotLoggedIn(userId)) {
            throw new PersonIsNotValidException("User is not logged-in");
        }

        if (inputIsNull(petRequest)) {
            throw new PetIsNotValidException("Input is null");
        }

        boolean petAlreadyExists = petRepository.existsByCode(petRequest.code);
        if (petAlreadyExists) {
            throw new PetIsNotValidException("Pet with code: " + petRequest.code + " already exists");
        }


        Person person = personRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("You don't have Person with id: " + userId + " saved in database."
                ));
        Color color = colorRepository.findById(petRequest.colorId).orElseThrow(
                () -> new EntityNotFoundException("You don't have Color with id: " + petRequest.colorId + " saved in database."
                ));


        Type type = typeRepository.findById(petRequest.typeId).orElseThrow(
                () -> new EntityNotFoundException("You don't have Type with id: " + petRequest.typeId + " saved in database."
                ));


        Pet pet;
        if (petRequest.countryId != null) {
            Country country = countryRepository.findById(petRequest.countryId).orElseThrow(
                    () -> new EntityNotFoundException("You don't have Country with id: " + petRequest.countryId + " saved in database."
                    ));
            pet = new Pet(petRequest.name, petRequest.code, person, type, color, country);
        } else {
            pet = new Pet(petRequest.name, petRequest.code, person, type, color);
        }


        petRepository.save(pet);


        return new PetResponse(
                pet.getId(),
                pet.getName(),
                pet.getCode(),
                pet.getPerson().getName(),
                pet.getType().getType(),
                pet.getColor().getColor(),
                (pet.getCountry() != null ? pet.getCountry().getCountry() : null),
                pet.getType().getId(),
                pet.getColor().getId(),
                (pet.getCountry() != null ? pet.getCountry().getId() : null));

    }


    /**
     * This method updates existing Pet in a database.
     *
     * @param petRequest new pet.
     * @param petId      id of a pet that needs to be updated
     * @param userId     id of a user that pet belongs to
     * @return PetResponse class or null.
     * @throws PersonIsNotValidException
     * @throws PetIsNotValidException
     */
    public PetResponse updatePet(PetRequest petRequest, Long petId, Long userId) throws PersonIsNotValidException, PetIsNotValidException {

        if (userIsNotLoggedIn(userId)) {
            throw new PersonIsNotValidException("User is not logged-in");
        }


        Pet pet = petRepository.findById(petId).orElseThrow(
                () -> new EntityNotFoundException("You don't have Pet with id: " + petId + " saved in database."
                ));

        if (!pet.getPerson().getId().equals(userId)) {
            throw new PersonIsNotValidException("User tried to edit pet that belongs to another user");
        }


        if (inputIsNull(petRequest)) {
            throw new PetIsNotValidException("Input is null");
        }

        Pet alreadySavedPet = petRepository.findByCode(petRequest.code);
        if (alreadySavedPet != null && !alreadySavedPet.getId().equals(petId)) {
            throw new PetIsNotValidException("Pet with code: " + petRequest.code + " already exists");
        }


        Person person = personRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("You don't have Person with id: " + userId + " saved in database."
                ));
        Color color = colorRepository.findById(petRequest.colorId).orElseThrow(
                () -> new EntityNotFoundException("You don't have Color with id: " + petRequest.colorId + " saved in database."
                ));

        Country country = new Country();
        if (petRequest.countryId != null) {
            country = countryRepository.findById(petRequest.countryId).orElseThrow(
                    () -> new EntityNotFoundException("You don't have Country with id: " + petRequest.countryId + " saved in database."
                    ));
        }

        Type type = typeRepository.findById(petRequest.typeId).orElseThrow(
                () -> new EntityNotFoundException("You don't have Type with id: " + petRequest.typeId + " saved in database."
                ));

        pet.setName(petRequest.getName());
        pet.setCode(petRequest.getCode());
        pet.setPerson(person);
        pet.setColor(color);
        if (petRequest.countryId != null) {
            pet.setCountry(country);
        } else {
            pet.setCountry(null);
        }
        pet.setType(type);

        petRepository.save(pet);


        return new PetResponse(
                pet.getId(),
                pet.getName(),
                pet.getCode(),
                pet.getPerson().getName(),
                pet.getType().getType(),
                pet.getColor().getColor(),
                (pet.getCountry() != null ? pet.getCountry().getCountry() : null),
                pet.getType().getId(),
                pet.getColor().getId(),
                (pet.getCountry() != null ? pet.getCountry().getId() : null));
    }


    /**
     * This method deletes Pet from a database.
     *
     * @param petId  ID (unique identification number) of the pet to delete
     * @param userId ID of a user that Pet belongs to
     * @throws PersonIsNotValidException
     */
    public void deletePet(Long petId, Long userId) throws PersonIsNotValidException {

        if (userIsNotLoggedIn(userId)) {
            throw new PersonIsNotValidException("User is not logged-in");
        }

        Pet pet = petRepository.findById(petId).orElseThrow(
                () -> new EntityNotFoundException("You don't have Pet with id: " + petId + " saved in database."
                ));

        if (!pet.getPerson().getId().equals(userId)) {
            throw new PersonIsNotValidException("User tried to Delete pet that belongs to another user");
        }

        petRepository.deleteById(petId);

    }


    private boolean userIsNotLoggedIn(Long userId) {
        if (userId == null) {
            return true;
        }
        return false;
    }


    private boolean inputIsNull(PetRequest petRequest) {
        if (petRequest.name == null ||
                petRequest.code == null ||
                petRequest.colorId == null ||
                //petRequest.countryId == null ||
                petRequest.typeId == null) {
            return true;
        }
        return false;
    }

}
