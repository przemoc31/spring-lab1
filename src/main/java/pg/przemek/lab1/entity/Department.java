package pg.przemek.lab1.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "department", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Doctor> doctors = new ArrayList<>();

    public Department(Department newDepartment)
    {
        this.name = newDepartment.getName();
        this.numberOfBeds = newDepartment.getNumberOfBeds();
        this.numberOfRooms = newDepartment.getNumberOfRooms();
        this.doctors = newDepartment.getDoctors();
    }
}
