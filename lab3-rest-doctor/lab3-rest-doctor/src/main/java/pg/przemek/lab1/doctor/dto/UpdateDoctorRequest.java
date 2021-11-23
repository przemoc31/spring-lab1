package pg.przemek.lab1.doctor.dto;

import lombok.*;
import pg.przemek.lab1.doctor.entity.Doctor;
import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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
