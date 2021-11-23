package pg.przemek.lab1.department.event.dto;

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

    public static Function<Department, CreateDepartmentRequest> entityToDtoMapper()
    {
        return entity -> CreateDepartmentRequest.builder()
                .name(entity.getName())
                .build();
    }
}
