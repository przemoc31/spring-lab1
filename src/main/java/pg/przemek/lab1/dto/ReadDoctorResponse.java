package pg.przemek.lab1.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pg.przemek.lab1.entity.Department;
import pg.przemek.lab1.entity.Doctor;

import java.util.function.Function;

@Getter
@Setter
@Builder
public class ReadDoctorResponse
{

    private Long id;

    private String name;

    private String surname;

    private String department;

    public static Function<Doctor, ReadDoctorResponse> entityToDtoMapper()
    {
        return doctor -> ReadDoctorResponse.builder()
                .id(doctor.getId())
                .name(doctor.getName())
                .surname(doctor.getSurname())
                .department(doctor.getDepartment().getName())
                .build();
    }
}
