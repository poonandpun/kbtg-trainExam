package tech.kbtg.BackendExam.service.Impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.kbtg.BackendExam.repository.EmployeeRepository;
import tech.kbtg.BackendExam.service.EmployeeService;
import tech.kbtg.BackendExam.entity.Employee;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EntityManager entityManager;
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Employee employee) {
        entityManager.persist(employee);
    }

    @Override
    public List<Employee> getAllEmployee() {
        TypedQuery<Employee> employeeTypedQuery =
                entityManager.createQuery("FROM Employee", Employee.class);
        return employeeTypedQuery.getResultList();
    }

    @Override
    public Employee getEmployeeWithID(Integer id) {
        TypedQuery<Employee> employeeTypedQuery =
                entityManager.createQuery("FROM Employee WHERE id = :id", Employee.class);
        return entityManager.find(Employee.class, id);
    }

    @Override
    @Transactional
    public List<Employee>  newEmployee(Employee employee) {
        entityManager.persist(employee);
        TypedQuery<Employee> employeeTypedQuery =
                entityManager.createQuery("FROM Employee", Employee.class);
        return employeeTypedQuery.getResultList();
    }

    @Override
    @Transactional
    public boolean deleteByID(Integer id) {
        boolean result = false;
        try {
            Employee employee = entityManager.find(Employee.class, id);
            entityManager.remove(employee);
            result = true;
        }
        catch (Exception e)
        {
            result = false;
        }
        return result;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        int id = employee.getId();
        Employee Bufferemploy = entityManager.find(Employee.class, id);
        Bufferemploy.setFirstname(employee.getFirstname());
        Bufferemploy.setLastname(employee.getLastname());
        Bufferemploy.setNickname(employee.getNickname());
        Bufferemploy.setAddress(employee.getAddress());

        entityManager.merge(employee);
        Employee result = entityManager.find(Employee.class, employee);
        return  result;

    }

    @Override
    public Employee updateSalary(int id) {
        Employee target = entityManager.find(Employee.class, id);
        double salaryBefore = Integer.parseInt(target.getSalary());
        double variable = salaryBefore * 0.2;
        salaryBefore = salaryBefore + variable;
        target.setSalary(String.valueOf(salaryBefore));
        entityManager.merge(target);
        return target;
    }

}
