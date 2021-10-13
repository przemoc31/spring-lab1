package pg.przemek.lab1.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pg.przemek.lab1.entity.Department;
import pg.przemek.lab1.entity.Doctor;
import pg.przemek.lab1.service.DepartmentService;
import pg.przemek.lab1.service.DoctorService;

import javax.annotation.PostConstruct;

@Component
public class Initializer
{
    private final DepartmentService departmentService;
    private final DoctorService doctorService;

    @Autowired
    public Initializer(DepartmentService departmentService, DoctorService doctorService)
    {
        this.departmentService = departmentService;
        this.doctorService = doctorService;
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

        Doctor watson = Doctor.builder()
                .id(1156)
                .name("John")
                .surname("Watson")
                .department(neurology)
                .build();

        Doctor house = Doctor.builder()
                .id(888)
                .name("Gregory")
                .surname("House")
                .department(cardiology)
                .build();

        Doctor murphy = Doctor.builder()
                .id(1723)
                .name("Shaun")
                .surname("Murphy")
                .department(surgery)
                .build();

        doctorService.save(watson);
        doctorService.save(house);
        doctorService.save(murphy);
    }
}
