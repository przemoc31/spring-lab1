package pg.przemek.lab1.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Doctor {
    /* primary key */
    private int id;
    private String name;
    private String surname;
    private Department department;

    public Doctor(Doctor newDoctor)
    {
        this.id = newDoctor.getId();
        this.name = newDoctor.getName();
        this.surname = newDoctor.getSurname();
        this.department = newDoctor.getDepartment();
    }
}
