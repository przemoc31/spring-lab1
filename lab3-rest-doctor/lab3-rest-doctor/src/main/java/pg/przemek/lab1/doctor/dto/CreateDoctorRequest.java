package pg.przemek.lab1.doctor.dto;

import lombok.*;
import pg.przemek.lab1.department.entity.Department;
import pg.przemek.lab1.doctor.entity.Doctor;
import java.util.function.Function;
import java.util.function.Supplier;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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
