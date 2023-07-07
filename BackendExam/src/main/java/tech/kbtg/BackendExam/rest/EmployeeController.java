package tech.kbtg.BackendExam.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.kbtg.BackendExam.service.EmployeeService;
import tech.kbtg.BackendExam.entity.Employee;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public ResponseEntity<?>  readAllEmployee(){
        try {
            List<Employee> employeeList = employeeService.getAllEmployee();
            return ResponseEntity.status( HttpStatus.OK ).body( employeeList );
        }
        catch (Exception e)
        {
            return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( e.getCause() );
        }
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

    @PutMapping("/{id}")
    public  ResponseEntity<?> updateEmployee(@PathVariable Integer id,@RequestBody Employee employee)
    {
        try {
            Employee resultEmployee = employeeService.updateEmployee(id,employee);
            return ResponseEntity.status( HttpStatus.OK ).body( resultEmployee );
        }
        catch (Exception e)
        {
            return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( e.getCause() );
        }
    }

    @PutMapping("/salary/{id}/{percent}")
    public  ResponseEntity<?> updateSalary(@PathVariable(name = "id") Integer id
            , @PathVariable(name = "percent") Integer percent)
    {
        try {
            Employee resultEmployee = employeeService.updateSalary(id, percent);
            return ResponseEntity.status( HttpStatus.OK ).body( resultEmployee );
        }
        catch (Exception e)
        {
            return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( e.getCause() );
        }
    }

    @PutMapping("/position/{id}/{position}")
    public  ResponseEntity<?> updatePositionByID(@PathVariable(name = "id") int id
            , @PathVariable(name = "position") String posintion)
    {
        try {
            Employee resultEmployee = employeeService.updatePosition(id, posintion);
            return ResponseEntity.status( HttpStatus.OK ).body( resultEmployee );
        }
        catch (Exception e)
        {
            return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( e.getCause() );
        }
    }
    @GetMapping("/name")
    public  ResponseEntity<?> searchByName(@RequestParam String q)
    {
        try {
            List<Employee> result = employeeService.searchEmployeeByName(q);
            return ResponseEntity.status( HttpStatus.OK ).body( result );
        }
        catch (Exception e)
        {
            return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( e.getCause() );
        }
    }

    @DeleteMapping("/")
    public  ResponseEntity<?> deleteMultiID(@RequestParam List<Integer> id)
    {
        try {
            List<Integer> result = employeeService.deleteMultiElement(id);
            if(result.size() > 0)
            {
                return ResponseEntity.status( HttpStatus.MULTI_STATUS).body( "not_found_ids_:" + result );
            }
            else
            {
                return ResponseEntity.status( HttpStatus.NO_CONTENT).body( result );
            }

        }
        catch (Exception e)
        {
            return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( e.getCause() );
        }
    }
}
