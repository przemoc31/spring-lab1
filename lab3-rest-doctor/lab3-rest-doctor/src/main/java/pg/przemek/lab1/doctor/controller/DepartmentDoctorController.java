package pg.przemek.lab1.doctor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pg.przemek.lab1.department.entity.Department;
import pg.przemek.lab1.department.service.DepartmentService;
import pg.przemek.lab1.doctor.dto.CreateDoctorRequest;
import pg.przemek.lab1.doctor.dto.ReadAllDoctorsResponse;
import pg.przemek.lab1.doctor.dto.ReadDoctorResponse;
import pg.przemek.lab1.doctor.dto.UpdateDoctorRequest;
import pg.przemek.lab1.doctor.entity.Doctor;
import pg.przemek.lab1.doctor.service.DoctorService;

import java.util.Optional;

@RestController
@RequestMapping("api/departments/{name}/doctors")
public class DepartmentDoctorController
{
    private DoctorService doctorService;

    private DepartmentService departmentService;

    @Autowired
    public DepartmentDoctorController(DoctorService doctorService, DepartmentService departmentService)
    {
        this.doctorService = doctorService;
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<ReadAllDoctorsResponse> readAllDoctors(@PathVariable("name") String departmentName)
    {
        Optional<Department> department = departmentService.find(departmentName);
        if(department.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            return ResponseEntity.ok(ReadAllDoctorsResponse.entityToDtoMapper().
                    apply(doctorService.findAll(department.get())));
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<ReadDoctorResponse> readDoctor(@PathVariable("name") String departmentName,
                                                         @PathVariable("id") Long doctorId)
    {
        Optional<Doctor> doctor = doctorService.find(doctorId, departmentName);
        if(doctor.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            return ResponseEntity.ok(ReadDoctorResponse.entityToDtoMapper().apply(doctor.get()));
        }
    }

    @PostMapping
    public ResponseEntity<Void> createDoctor(@PathVariable("name") String departmentName,
                                             @RequestBody CreateDoctorRequest createDoctorRequest,
                                             UriComponentsBuilder uriComponentsBuilder)
    {
        Optional<Department> department = departmentService.find(departmentName);
        if(department.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            Doctor doctor = CreateDoctorRequest
                    .dtoToEntityMapper(department.get())
                    .apply(createDoctorRequest);
            doctor = doctorService.save(doctor);
            return ResponseEntity.created(uriComponentsBuilder
                    .pathSegment("api", "departments", "{name}", "doctors", "{id}")
                    .buildAndExpand(department.get().getName(), doctor.getId()).toUri()).build();
        }
    }

    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteDoctor(@PathVariable("name") String departmentName, @PathVariable("id") Long doctorId)
    {
        Optional<Doctor> doctor = doctorService.find(doctorId);
        if(doctor.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            doctorService.delete(doctorId);
            return ResponseEntity.accepted().build();
        }
    }

    @PutMapping("{id}")
    ResponseEntity<Void> updateDoctor(@PathVariable("name") String departmentName, @PathVariable("id") Long doctorId,
                                      @RequestBody UpdateDoctorRequest updateDoctorRequest)
    {
        Optional<Doctor> doctor = doctorService.find(doctorId);
        if(doctor.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            UpdateDoctorRequest.dtoToEntityUpdater().apply(doctor.get(), updateDoctorRequest);
            doctorService.update(doctor.get());
            return ResponseEntity.accepted().build();
        }
    }
}
