package tech.kbtg.BackendExam.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.kbtg.BackendExam.service.EmployeeService;
import tech.kbtg.BackendExam.entity.Employee;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public List<Employee> readAllEmployee(){
        List<Employee> employeeList = employeeService.getAllEmployee();
        return  employeeList;
    }

    @GetMapping("/{id}")
    public Employee readAllEmployeeID(@PathVariable(name = "id") Integer id)
    {
        Employee employee = employeeService.getEmployeeWithID(id);
        return employee;
    }

    @PostMapping("/")
    public List<Employee> NewEmployee(@RequestBody Employee newMember)
    {
        newMember.setId(0);
        newMember.setStatus("Current");
        List<Employee> resultFromNew = employeeService.newEmployee(newMember);

        return  resultFromNew;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(name = "id") Integer id)
    {
        boolean resultFromDelete = employeeService.deleteByID(id);
        if(resultFromDelete)
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(HttpStatus.NO_CONTENT);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/")
    public  ResponseEntity<?> updateEmployee(@RequestBody Employee employee)
    {
        try {
            Employee resultEmployee = employeeService.updateEmployee(employee);
            return ResponseEntity.status( HttpStatus.OK ).body( resultEmployee );
        }
        catch (Exception e)
        {
            return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( e.getCause() );
        }
    }

    @PutMapping("/salary/{id}")
    public  ResponseEntity<?> updateSalary(@PathVariable(name = "id") Integer id)
    {
        try {
            Employee resultEmployee = employeeService.updateSalary(id);
            return ResponseEntity.status( HttpStatus.OK ).body( resultEmployee );
        }
        catch (Exception e)
        {
            return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( e.getCause() );
        }
    }
}
