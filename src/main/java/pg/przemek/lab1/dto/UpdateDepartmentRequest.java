package pg.przemek.lab1.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pg.przemek.lab1.entity.Department;
import pg.przemek.lab1.entity.Doctor;
import java.util.List;
import java.util.function.BiFunction;

@Getter
@Setter
@Builder
public class UpdateDepartmentRequest
{
    private int numberOfBeds;

    private int numberOfRooms;

    //private List<Doctor> doctors;

    public static BiFunction<Department, UpdateDepartmentRequest, Department> dtoToEntityUpdater()
    {
        return (department, updateDepartmentRequest) ->
        {
            department.setNumberOfBeds(updateDepartmentRequest.getNumberOfBeds());
            department.setNumberOfRooms(updateDepartmentRequest.getNumberOfRooms());
            //department.setDoctors(updateDepartmentRequest.getDoctors());
            return department;
        };
    }
}
