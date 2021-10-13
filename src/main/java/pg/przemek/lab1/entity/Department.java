package pg.przemek.lab1.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Department {
    /* primary key */
    private String name;
    private int numberOfBeds;
    private int numberOfRooms;

    public Department(Department newDepartment)
    {
        this.name = newDepartment.getName();
        this.numberOfBeds = newDepartment.getNumberOfBeds();
        this.numberOfRooms = newDepartment.getNumberOfRooms();
    }
}
