package ee.wisercat.PetManagement.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.SEQUENCE;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "country")
public class Country {

    @Id
    @SequenceGenerator(
            name = "country_id_seq",
            sequenceName = "country_id_seq",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "country_id_seq"
    )
    Long id;

    @Column(name = "country")
    private String country;


/*    @OneToMany(mappedBy="country")
    private Set<Pet> pets;*/
}
