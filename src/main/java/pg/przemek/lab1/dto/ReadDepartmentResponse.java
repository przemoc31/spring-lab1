package pg.przemek.lab1.dto;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import pg.przemek.lab1.entity.Department;
import pg.przemek.lab1.entity.Doctor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class ReadDepartmentResponse
{

    private String name;

    private int numberOfBeds;

    private int numberOfRooms;

    private List<String> doctors;

    public static Function<Department, ReadDepartmentResponse> entityToDtoMapper()
    {
        return department -> ReadDepartmentResponse.builder()
                .name(department.getName())
                .numberOfBeds(department.getNumberOfBeds())
                .numberOfRooms(department.getNumberOfRooms())
                .doctors(department.getDoctors().stream().map(element -> "id: " + element.getId() + ", name: " + element.getName() + ", surname: " + element.getSurname()).collect(Collectors.toList()))
                .build();
    }
}
