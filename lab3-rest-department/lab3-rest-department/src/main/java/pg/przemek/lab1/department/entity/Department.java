package pg.przemek.lab1.department.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "departments")
public class Department
{

    @Id
    private String name;

    @Column(name = "number_of_beds")
    private int numberOfBeds;

    @Column(name = "number_of_rooms")
    private int numberOfRooms;

    public Department(Department newDepartment)
    {
        this.name = newDepartment.getName();
        this.numberOfBeds = newDepartment.getNumberOfBeds();
        this.numberOfRooms = newDepartment.getNumberOfRooms();
    }
}
