package pg.przemek.lab1.department.dto;

import lombok.*;
import pg.przemek.lab1.department.entity.Department;

import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReadDepartmentResponse
{

    private String name;

    private int numberOfBeds;

    private int numberOfRooms;

    public static Function<Department, ReadDepartmentResponse> entityToDtoMapper()
    {
        return department -> ReadDepartmentResponse.builder()
                .name(department.getName())
                .numberOfBeds(department.getNumberOfBeds())
                .numberOfRooms(department.getNumberOfRooms())
                .build();
    }
}
