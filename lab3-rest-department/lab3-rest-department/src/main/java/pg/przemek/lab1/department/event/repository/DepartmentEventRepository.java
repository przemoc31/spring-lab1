package pg.przemek.lab1.department.event.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import pg.przemek.lab1.department.entity.Department;
import pg.przemek.lab1.department.event.dto.CreateDepartmentRequest;

@Repository
public class DepartmentEventRepository
{

    private RestTemplate restTemplate;

    @Autowired
    public DepartmentEventRepository(@Value("${labAUI.doctors.url}") String baseUrl)
    {
        this.restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void create(Department department)
    {
        restTemplate.postForLocation("/departments", CreateDepartmentRequest.entityToDtoMapper().apply(department));
    }

    public void delete(Department department)
    {
        restTemplate.delete("/departments/{departmentName}", department.getName());
    }

}
