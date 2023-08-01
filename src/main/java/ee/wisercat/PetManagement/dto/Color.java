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
@Table(name = "color")
public class Color {
    @Id
    @SequenceGenerator(
            name = "color_id_seq",
            sequenceName = "color_id_seq",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "color_id_seq"
    )
    Long id;

    @Column(name = "color")
    private String color;


/*    @OneToMany(mappedBy="color")
    private Set<Pet> pets;*/
}
