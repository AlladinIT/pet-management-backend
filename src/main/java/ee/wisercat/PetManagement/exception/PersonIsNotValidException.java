package ee.wisercat.PetManagement.exception;

public class PersonIsNotValidException extends Exception {

    public PersonIsNotValidException(String personIsNotValid) {
        super(personIsNotValid);
    }
}
