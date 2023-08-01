package ee.wisercat.PetManagement.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetResponse {
    public Long id;
    public String name;
    public Long code;
    public String personName;
    public String type;
    public String color;
    public String country;

    public Long typeId;
    public Long colorId;
    public Long countryId;
}
