package tech.kbtg.BackendExam.service.Impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import tech.kbtg.BackendExam.repository.EmployeeRepository;
import tech.kbtg.BackendExam.service.EmployeeService;
import tech.kbtg.BackendExam.entity.Employee;

import java.util.ArrayList;
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
    @Transactional
    public Employee updateEmployee(Integer id,Employee employee) {
        Employee Bufferemploy = entityManager.find(Employee.class, id);
        System.out.print(Bufferemploy);
        Bufferemploy.setFirstname(employee.getFirstname().toString());
        Bufferemploy.setLastname(employee.getLastname());
        Bufferemploy.setNickname(employee.getNickname());
        Bufferemploy.setAddress(employee.getAddress());

        entityManager.merge(Bufferemploy);
        Employee result = entityManager.find(Employee.class, id);
        return  result;

    }

    @Override
    @Transactional
    public Employee updateSalary(int id, Integer number) {
        Employee target = entityManager.find(Employee.class, id);
        double salaryBefore = target.getSalary();
        double variable = (salaryBefore * number)/100;
        salaryBefore = salaryBefore + variable;
        target.setSalary(salaryBefore);
        entityManager.merge(target);
        return target;
    }

    @Override
    public List<Employee> searchEmployeeByName(String name) {
        TypedQuery<Employee> employeeTypedQuery =
                entityManager.createQuery("FROM Employee", Employee.class);
        List<Employee> checkingMaster = employeeTypedQuery.getResultList();
        List<Employee> result = new ArrayList<Employee>();
        for (Employee buffer: checkingMaster) {
            if(buffer.getFirstname().contains(name))
            {
                result.add(buffer);
            }
        }
        return result;
    }

    @Override
    @Transactional
    public Employee updatePosition(int id, String position) {
        Employee target = entityManager.find(Employee.class, id);
        target.setPosition(position);
        entityManager.merge(target);
        return target;
    }

    @Override
    @Transactional
    public List<Integer> deleteMultiElement(List<Integer> id) {
        List<Integer> result = new ArrayList<>();

        for (Integer bufferID:id)
        {
            Employee reciveByQuerry = entityManager.find(Employee.class, bufferID);
            if(reciveByQuerry == null)
            {
                result.add(bufferID);
            }
            else
            {
                entityManager.remove(reciveByQuerry);
            }
        }
        return result;
    }


}
