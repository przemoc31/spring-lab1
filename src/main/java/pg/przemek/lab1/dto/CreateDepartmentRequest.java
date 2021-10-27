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
public class CreateDepartmentRequest
{
    private String name;

    private int numberOfBeds;

    private int numberOfRooms;

    public static Function<CreateDepartmentRequest, Department> dtoToEntityMapper(List<Doctor> doctors)
    {
        return createDepartmentRequest -> Department.builder()
                .name(createDepartmentRequest.getName())
                .numberOfBeds(createDepartmentRequest.getNumberOfBeds())
                .numberOfRooms(createDepartmentRequest.getNumberOfRooms())
                .doctors(doctors)
                .build();
    }
}
