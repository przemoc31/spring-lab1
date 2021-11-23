package pg.przemek.lab1.doctor.dto;

import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReadAllDoctorsResponse
{

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Doctor
    {

        private Long id;

        private String name;

        private String surname;
    }

    @Singular
    private List<Doctor> doctors;

    public static Function<Collection<pg.przemek.lab1.doctor.entity.Doctor>, ReadAllDoctorsResponse> entityToDtoMapper()
    {
        return doctors ->
        {
            ReadAllDoctorsResponseBuilder response = ReadAllDoctorsResponse.builder();
            doctors.stream()
                    .map(doctor -> Doctor.builder()
                            .id(doctor.getId())
                            .name(doctor.getName())
                            .surname(doctor.getSurname())
                            .build())
                    .forEach(response::doctor);
            return response.build();
        };
    }
}