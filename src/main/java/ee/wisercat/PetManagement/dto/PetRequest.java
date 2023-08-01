package ee.wisercat.PetManagement.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetRequest {
    public String name;
    public Long code;
    public Long typeId;
    public Long colorId;
    public Long countryId;
}
