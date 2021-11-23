package pg.przemek.lab1.department.dto;

import lombok.*;
import pg.przemek.lab1.department.entity.Department;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateDepartmentRequest
{
    private String name;

    private int numberOfBeds;

    private int numberOfRooms;

    public static Function<CreateDepartmentRequest, Department> dtoToEntityMapper()
    {
        return createDepartmentRequest -> Department.builder()
                .name(createDepartmentRequest.getName())
                .numberOfBeds(createDepartmentRequest.getNumberOfBeds())
                .numberOfRooms(createDepartmentRequest.getNumberOfRooms())
                .build();
    }
}
