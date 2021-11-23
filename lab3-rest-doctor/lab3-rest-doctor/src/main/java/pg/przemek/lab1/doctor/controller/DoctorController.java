package pg.przemek.lab1.doctor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pg.przemek.lab1.department.service.DepartmentService;
import pg.przemek.lab1.doctor.entity.Doctor;
import pg.przemek.lab1.doctor.dto.*;
import pg.przemek.lab1.department.entity.Department;
import pg.przemek.lab1.doctor.service.DoctorService;

import javax.print.Doc;
import java.util.Optional;

@RestController
@RequestMapping("api/doctors")
public class DoctorController
{

    private DoctorService doctorService;

    private DepartmentService departmentService;

    @Autowired
    public DoctorController(DoctorService doctorService, DepartmentService departmentService)
    {
        this.doctorService = doctorService;
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<ReadAllDoctorsResponse> readAllDoctors()
    {
        return ResponseEntity.ok(ReadAllDoctorsResponse.entityToDtoMapper().apply(doctorService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<ReadDoctorResponse> readDoctor(@PathVariable("id") long id)
    {
        Optional<Doctor> doctor = doctorService.find(id);
        if (doctor.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            return ResponseEntity.ok(ReadDoctorResponse.entityToDtoMapper().apply(doctor.get()));
        }
    }

    @PostMapping
    public ResponseEntity<Void> createDoctor(@RequestBody CreateDoctorRequest createDoctorRequest, UriComponentsBuilder uriComponentsBuilder)
    {
        Optional<Department> department = departmentService.find(createDoctorRequest.getDepartment());
        if (department.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            Doctor doctor = CreateDoctorRequest
                    .dtoToEntityMapper(department.get())
                    .apply(createDoctorRequest);
            doctor = doctorService.save(doctor);
            return ResponseEntity.created(uriComponentsBuilder.pathSegment("api", "doctors", "{id}").buildAndExpand(doctor.getId()).toUri()).build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable("id") long id)
    {
        Optional<Doctor> doctor = doctorService.find(id);
        if (doctor.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            doctorService.delete(id);
            return ResponseEntity.accepted().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateDoctor(@RequestBody UpdateDoctorRequest updateDoctorRequest, @PathVariable("id") long id)
    {
        Optional<Doctor> doctor = doctorService.find(id);
        if (doctor.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            UpdateDoctorRequest.dtoToEntityUpdater()
                    .apply(doctor.get(), updateDoctorRequest);
            doctorService.update(doctor.get());
            return ResponseEntity.accepted().build();
        }

    }

}
