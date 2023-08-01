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
@Table(name = "type")
public class Type {
    @Id
    @SequenceGenerator(
            name = "type_id_seq",
            sequenceName = "type_id_seq",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "type_id_seq"
    )
    Long id;

    @Column(name = "type")
    private String type;

/*    @OneToMany(mappedBy="type")
    private Set<Pet> pets;*/
}
