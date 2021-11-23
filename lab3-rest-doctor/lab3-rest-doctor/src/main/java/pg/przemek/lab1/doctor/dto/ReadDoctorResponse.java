package pg.przemek.lab1.doctor.dto;

import lombok.*;
import pg.przemek.lab1.doctor.entity.Doctor;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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
