package pg.przemek.lab1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pg.przemek.lab1.entity.Department;
import pg.przemek.lab1.entity.Doctor;
import pg.przemek.lab1.service.DepartmentService;
import pg.przemek.lab1.service.DoctorService;
import java.util.Scanner;

@Component
public class CommandLine implements CommandLineRunner
{

    private DepartmentService departmentService;
    private DoctorService doctorService;

    @Autowired
    public CommandLine(DepartmentService departmentService, DoctorService doctorService)
    {
        this.departmentService = departmentService;
        this.doctorService = doctorService;
    }

    @Override
    public void run(String... args) throws Exception
    {
        showMenu();
    }

    public void showMenu()
    {
        System.out.println("MENU:");
        System.out.println("1. List all categories");
        System.out.println("2. List all elements");
        System.out.println("3. Add new element");
        System.out.println("4. Delete existing element");
        System.out.println("5. Stop the application");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();

        switch (input)
        {
            case 1:
                listCategories();
                break;
            case 2:
                listElements();
                break;
            case 3:
                addElement();
                break;
            case 4:
                deleteElement();
                break;
            case 5:
                System.exit(0);
                break;
            default:
                showMenu();
        }
    }
    public void listCategories()
    {
        departmentService.findAll().forEach(System.out::println);
        showMenu();
    }

    public void listElements()
    {
        doctorService.findAll().forEach(System.out::println);
        showMenu();
    }

    public void addElement()
    {
        String departmentClassName = departmentService.findAll().get(0).getClass().getName();
        String doctorClassName = doctorService.findAll().get(0).getClass().getName();
        int pointToTrim = departmentClassName.lastIndexOf ('.') + 1;
        System.out.println("What is the type of object you want to add?");
        System.out.println("1." + departmentClassName.substring(pointToTrim));
        System.out.println("2." + doctorClassName.substring(pointToTrim));
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();

        switch (input)
        {
            case 1:
                addDepartment();
                break;
            case 2:
                addDoctor();
                break;
        }
        showMenu();
    }

    public void addDepartment()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type name:");
        String name = scanner.nextLine();
        System.out.println("Type number of beds:");
        int numberOfBeds = scanner.nextInt();
        System.out.println("Type number of rooms:");
        int numberOfRooms = scanner.nextInt();
        Department newDepartment = new Department(name, numberOfBeds, numberOfRooms, null);
        departmentService.save(newDepartment);
    }

    public void addDoctor()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type id:");
        Long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Type name:");
        String name = scanner.nextLine();
        System.out.println("Type surname:");
        String surname = scanner.nextLine();
        System.out.println("Type name of department:");
        String departmentName = scanner.nextLine();
        Department department = departmentService.find(departmentName).orElse(null);
        Doctor newDoctor = new Doctor(id, name, surname, department);
        doctorService.save(newDoctor);
    }

    public void deleteElement()
    {
        String departmentClassName = departmentService.findAll().get(0).getClass().getName();
        String doctorClassName = doctorService.findAll().get(0).getClass().getName();
        int pointToTrim = departmentClassName.lastIndexOf ('.') + 1;
        System.out.println("What is the type of object you want to delete?");
        System.out.println("1." + departmentClassName.substring(pointToTrim));
        System.out.println("2." + doctorClassName.substring(pointToTrim));
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();

        switch (input)
        {
            case 1:
                deleteDepartment();
                break;
            case 2:
                deleteDoctor();
                break;
        }
        showMenu();
    }

    public void deleteDepartment()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type name of department you want to delete:");
        String name = scanner.nextLine();
        departmentService.delete(name);
    }

    public void deleteDoctor()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type id of doctor you want to delete:");
        Long id = scanner.nextLong();
        doctorService.delete(id);
    }
}
