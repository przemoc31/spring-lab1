package pg.przemek.lab1.department.entity;

import lombok.*;
import pg.przemek.lab1.doctor.entity.Doctor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "departments")
public class Department implements Serializable
{

    @Id
    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Doctor> doctors = new ArrayList<>();

    public Department(Department newDepartment)
    {
        this.name = newDepartment.getName();
        this.doctors = newDepartment.getDoctors();
    }
}
