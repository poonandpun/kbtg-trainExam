package tech.kbtg.BackendExam.service;

import tech.kbtg.BackendExam.entity.Employee;

import java.util.List;

public interface EmployeeService {
    void save (Employee employee);
    List<Employee> getAllEmployee();
    Employee getEmployeeWithID(Integer id);

    List<Employee> newEmployee(Employee employee);

    boolean deleteByID (Integer id);

    Employee updateEmployee (Integer id,Employee employee);
    Employee updateSalary (int id, Integer number);

    List<Employee> searchEmployeeByName(String name);

    Employee updatePosition(int id, String position);

    List<Integer> deleteMultiElement(List<Integer> id);
}
