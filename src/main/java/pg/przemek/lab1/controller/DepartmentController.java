package pg.przemek.lab1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pg.przemek.lab1.dto.*;
import pg.przemek.lab1.entity.Department;
import pg.przemek.lab1.entity.Doctor;
import pg.przemek.lab1.service.DepartmentService;
import pg.przemek.lab1.service.DoctorService;

import java.util.Optional;

@RestController
@RequestMapping("api/departments")
public class DepartmentController
{
    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService)
    {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<ReadAllDepartmentsResponse> readAllDepartments()
    {
        return ResponseEntity.ok(ReadAllDepartmentsResponse.entityToDtoMapper().apply(departmentService.findAll()));
    }

    @GetMapping("{name}")
    public ResponseEntity<ReadDepartmentResponse> readDepartment(@PathVariable("name") String name)
    {
        Optional<Department> department = departmentService.find(name);
        if (department.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            return ResponseEntity.ok(ReadDepartmentResponse.entityToDtoMapper().apply(department.get()));
        }
    }

    @PostMapping
    public ResponseEntity<CreateDepartmentRequest> createDepartment(@RequestBody CreateDepartmentRequest createDepartmentRequest, UriComponentsBuilder uriComponentsBuilder)
    {
        Optional<Department> optionalDepartment = departmentService.find(createDepartmentRequest.getName());
        if (optionalDepartment.isEmpty())
        {
            Department department = CreateDepartmentRequest
                    .dtoToEntityMapper(null)
                    .apply(createDepartmentRequest);
            department = departmentService.save(department);
            return ResponseEntity.created(uriComponentsBuilder.pathSegment("api", "departments", "{id}").buildAndExpand(department.getName()).toUri()).build();
        }
        else
        {
            //  Already Exists Error
            return ResponseEntity.status(403).build();
        }
    }

    @DeleteMapping("{name}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable("name") String name)
    {
        Optional<Department> department = departmentService.find(name);
        if (department.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            departmentService.delete(name);
            return ResponseEntity.accepted().build();
        }
    }

    @PutMapping("{name}")
    public ResponseEntity<Void> updateDepartment(@RequestBody UpdateDepartmentRequest updateDepartmentRequest, @PathVariable("name") String name)
    {
        Optional<Department> department = departmentService.find(name);
        if (department.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            UpdateDepartmentRequest.dtoToEntityUpdater()
                            .apply(department.get(), updateDepartmentRequest);
            departmentService.update(department.get());
            return ResponseEntity.accepted().build();
        }
    }
}
