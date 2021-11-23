package pg.przemek.lab1.department.dto;

import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReadAllDepartmentsResponse
{

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Department
    {

        private String name;

        private int numberOfBeds;

        private int numberOfRooms;
    }

    @Singular
    private List<Department> departments;

    public static Function<Collection<pg.przemek.lab1.department.entity.Department>, ReadAllDepartmentsResponse> entityToDtoMapper()
    {
        return departments ->
        {
            ReadAllDepartmentsResponseBuilder response = ReadAllDepartmentsResponse.builder();
            departments.stream()
                    .map(department -> Department.builder()
                            .name(department.getName())
                            .numberOfBeds(department.getNumberOfBeds())
                            .numberOfRooms(department.getNumberOfRooms())
                            .build())
                    .forEach(response::department);
            return response.build();
        };
    }
}
