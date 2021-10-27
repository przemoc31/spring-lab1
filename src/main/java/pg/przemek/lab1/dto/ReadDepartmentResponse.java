package pg.przemek.lab1.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pg.przemek.lab1.entity.Department;
import pg.przemek.lab1.entity.Doctor;

import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
public class ReadDepartmentResponse
{

    private String name;

    private int numberOfBeds;

    private int numberOfRooms;

    //private List<Doctor> doctors;

    public static Function<Department, ReadDepartmentResponse> entityToDtoMapper()
    {
        return department -> ReadDepartmentResponse.builder()
                .name(department.getName())
                .numberOfBeds(department.getNumberOfBeds())
                .numberOfRooms(department.getNumberOfRooms())
                //.doctors(department.getDoctors())
                .build();
    }
}
