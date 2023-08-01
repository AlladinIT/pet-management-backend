package ee.wisercat.PetManagement.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.SEQUENCE;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "person")
@Builder
public class Person {

    @Id
    @SequenceGenerator(
            name = "person_id_seq",
            sequenceName = "person_id_seq",
            allocationSize = 1,
            initialValue = 4
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "person_id_seq"
    )
    Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;


/*    @OneToMany(mappedBy= "person")
    private Set<Pet> pets;*/

}
