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
@Table(name = "pet")
public class Pet {

    @Id
    @SequenceGenerator(
            name = "pet_id_seq",
            sequenceName = "pet_id_seq",
            allocationSize = 1,
            initialValue = 4
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "pet_id_seq"
    )
    Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private Long code;


    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private Type type;

    @ManyToOne
    @JoinColumn(name = "color_id", nullable = false)
    private Color color;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = true)
    private Country country;

    public Pet(String name, Long code, Person person, Type type, Color color, Country country) {
        this.name = name;
        this.code = code;
        this.person = person;
        this.type = type;
        this.color = color;
        this.country = country;
    }

    public Pet(String name, Long code, Person person, Type type, Color color) {
        this.name = name;
        this.code = code;
        this.person = person;
        this.type = type;
        this.color = color;
    }
}

