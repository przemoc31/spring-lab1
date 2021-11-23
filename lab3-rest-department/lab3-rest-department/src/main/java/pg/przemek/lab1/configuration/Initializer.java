package pg.przemek.lab1.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pg.przemek.lab1.department.entity.Department;
import pg.przemek.lab1.department.service.DepartmentService;

import javax.annotation.PostConstruct;

@Component
public class Initializer
{

    private final DepartmentService departmentService;

    @Autowired
    public Initializer(DepartmentService departmentService)
    {
        this.departmentService = departmentService;
    }

    @PostConstruct
    private synchronized void init()
    {
        Department cardiology = Department.builder()
                .name("Cardiology")
                .numberOfBeds(50)
                .numberOfRooms(20)
                .build();

        Department neurology = Department.builder()
                .name("Neurology")
                .numberOfBeds(80)
                .numberOfRooms(28)
                .build();

        Department surgery = Department.builder()
                .name("Surgery")
                .numberOfBeds(150)
                .numberOfRooms(60)
                .build();

        departmentService.save(cardiology);
        departmentService.save(neurology);
        departmentService.save(surgery);
    }
}
