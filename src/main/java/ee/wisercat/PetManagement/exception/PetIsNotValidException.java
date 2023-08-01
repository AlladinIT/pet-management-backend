package ee.wisercat.PetManagement.exception;

public class PetIsNotValidException extends Exception {

    public PetIsNotValidException(String petIsNotValid) {
        super(petIsNotValid);
    }
}
