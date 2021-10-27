package pg.przemek.lab1.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pg.przemek.lab1.entity.Department;
import pg.przemek.lab1.entity.Doctor;
import java.util.function.BiFunction;

@Getter
@Setter
@Builder
public class UpdateDoctorRequest
{

    private String name;

    private String surname;

    public static BiFunction<Doctor, UpdateDoctorRequest, Doctor> dtoToEntityUpdater()
    {
       return (doctor, updateDoctorRequest) ->
       {
           doctor.setName(updateDoctorRequest.getName());
           doctor.setSurname(updateDoctorRequest.getSurname());
           return doctor;
       };
    }

}
