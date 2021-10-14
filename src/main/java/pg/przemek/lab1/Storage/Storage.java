package pg.przemek.lab1.Storage;

import org.springframework.stereotype.Component;
import pg.przemek.lab1.entity.Department;
import pg.przemek.lab1.entity.Doctor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class Storage {
    private List<Department> departments = new ArrayList<Department>();
    private List<Doctor> doctors = new ArrayList<Doctor>();

    public synchronized void saveDepartment(Department newDepartment) throws IllegalArgumentException
    {
        for (Department department : departments)
        {
            if (department.getName().equals(newDepartment.getName()))
            {
                throw new IllegalArgumentException("This department name already exists");
            }
        }
        departments.add(newDepartment);
    }

    public Optional<Department> findDepartment(String name)
    {
        return departments.stream().filter(department -> department.getName().equals(name))
                .findFirst()
                .map(department -> new Department(department));
<<<<<<< HEAD
=======

>>>>>>> f9e8af5 (First Commit)
    }

    public List<Department> findAllDepartments()
    {
        return new ArrayList<Department>(departments);
    }

    public synchronized void deleteDepartment(String name) throws IllegalArgumentException
    {
        for (Department department : departments)
        {
            if (department.getName().equals(name))
            {
                departments.remove(department);
                return;
            }
        }
        throw new IllegalArgumentException("There is no department with this name");
    }

    public synchronized void saveDoctor(Doctor newDoctor) throws IllegalArgumentException
    {
        for (Doctor doctor : doctors)
        {
            if (doctor.getId() == newDoctor.getId())
            {
                throw new IllegalArgumentException("This id already exists");
            }
        }
        doctors.add(newDoctor);
    }

    public Optional<Doctor> findDoctor(int id)
    {
        return doctors.stream().filter(engine -> engine.getId() == id)
                .findFirst()
                .map(doctor -> new Doctor(doctor));
    }

    public List<Doctor> findAllDoctors()
    {
        return new ArrayList<Doctor>(doctors);
    }

    public synchronized void deleteDoctor(int id) throws IllegalArgumentException
    {
        for (Doctor doctor : doctors)
        {
            if (doctor.getId() == id)
            {
                doctors.remove(doctor);
                return;
            }
        }
        throw new IllegalArgumentException("There is no doctor with this id");
    }
}
