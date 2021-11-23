package pg.przemek.lab1.doctor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pg.przemek.lab1.department.entity.Department;
import pg.przemek.lab1.department.repository.DepartmentRepository;
import pg.przemek.lab1.department.service.DepartmentService;
import pg.przemek.lab1.doctor.entity.Doctor;
import pg.przemek.lab1.doctor.repository.DoctorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService
{

    private DoctorRepository doctorRepository;

    private DepartmentRepository departmentRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository, DepartmentRepository departmentRepository)
    {
        this.doctorRepository = doctorRepository;
        this.departmentRepository = departmentRepository;
    }

    public Optional<Doctor> find(Long id) {
        return doctorRepository.findById(id);
    }

    public Optional<Doctor> find(Long id, String departmentName)
    {
        Optional<Department> department = departmentRepository.findById(departmentName);
        if(department.isEmpty())
        {
            return Optional.empty();
        }
        else
        {
            return doctorRepository.findByIdAndDepartment(id, department.get());
        }
    }

    public List<Doctor> findAll() { return doctorRepository.findAll(); }

    public List<Doctor> findAll(Department department) { return doctorRepository.findAllByDepartment(department); }

    @Transactional
    public Doctor save(Doctor doctor) { return doctorRepository.save(doctor); }

    @Transactional
    public void delete(Long id) { doctorRepository.deleteById(id); }

    @Transactional
    public void update(Doctor doctor) { doctorRepository.save(doctor); }
}
