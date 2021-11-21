package pg.przemek.lab1.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "doctors")
public class Doctor
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String surname;

    @ManyToOne
    @JoinColumn(name = "department")
    private Department department;

    public Doctor(Doctor newDoctor)
    {
        this.id = newDoctor.getId();
        this.name = newDoctor.getName();
        this.surname = newDoctor.getSurname();
        this.department = newDoctor.getDepartment();
    }
}
