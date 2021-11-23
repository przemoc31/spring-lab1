package pg.przemek.lab1.department.dto;

import lombok.*;
import pg.przemek.lab1.department.entity.Department;
import pg.przemek.lab1.doctor.entity.Doctor;

import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateDepartmentRequest
{
    private String name;

    public static Function<CreateDepartmentRequest, Department> dtoToEntityMapper()
    {
        return createDepartmentRequest -> Department.builder()
                .name(createDepartmentRequest.getName())
                .build();
    }
}
