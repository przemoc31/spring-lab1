package pg.przemek.lab1.department.dto;

import lombok.*;
import pg.przemek.lab1.department.entity.Department;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateDepartmentRequest
{
    private int numberOfBeds;

    private int numberOfRooms;

    public static BiFunction<Department, UpdateDepartmentRequest, Department> dtoToEntityUpdater()
    {
        return (department, updateDepartmentRequest) ->
        {
            department.setNumberOfBeds(updateDepartmentRequest.getNumberOfBeds());
            department.setNumberOfRooms(updateDepartmentRequest.getNumberOfRooms());
            return department;
        };
    }
}
