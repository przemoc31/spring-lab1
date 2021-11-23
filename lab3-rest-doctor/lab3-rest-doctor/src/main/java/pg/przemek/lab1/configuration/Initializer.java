package pg.przemek.lab1.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pg.przemek.lab1.department.entity.Department;
import pg.przemek.lab1.department.service.DepartmentService;
import pg.przemek.lab1.doctor.entity.Doctor;
import pg.przemek.lab1.doctor.service.DoctorService;

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
                .build();

        Department neurology = Department.builder()
                .name("Neurology")
                .build();

        Department surgery = Department.builder()
                .name("Surgery")
                .build();

        departmentService.save(cardiology);
        departmentService.save(neurology);
        departmentService.save(surgery);

        Doctor watson = Doctor.builder()
                .name("John")
                .surname("Watson")
                .department(neurology)
                .build();

        Doctor house = Doctor.builder()
                .name("Gregory")
                .surname("House")
                .department(cardiology)
                .build();

        Doctor murphy = Doctor.builder()
                .name("Shaun")
                .surname("Murphy")
                .department(surgery)
                .build();

        doctorService.save(watson);
        doctorService.save(house);
        doctorService.save(murphy);
    }
}
