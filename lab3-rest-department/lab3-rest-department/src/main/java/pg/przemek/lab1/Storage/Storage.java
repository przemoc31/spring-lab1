package pg.przemek.lab1.Storage;

import org.springframework.stereotype.Component;
import pg.przemek.lab1.department.entity.Department;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class Storage {
    private List<Department> departments = new ArrayList<Department>();

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
}
