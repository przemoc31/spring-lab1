package pg.przemek.lab1.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pg.przemek.lab1.entity.Department;
import pg.przemek.lab1.entity.Doctor;
import java.util.function.Function;

@Getter
@Setter
@Builder
public class CreateDoctorRequest
{

    private String name;

    private String surname;

    private String department;

    public static Function<CreateDoctorRequest, Doctor> dtoToEntityMapper(Department department)
    {
        return createDoctorRequest -> Doctor.builder()
                .name(createDoctorRequest.getName())
                .surname(createDoctorRequest.getSurname())
                .department(department)
                .build();
    }

}
