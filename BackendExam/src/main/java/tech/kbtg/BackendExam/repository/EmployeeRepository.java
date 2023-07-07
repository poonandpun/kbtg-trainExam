package tech.kbtg.BackendExam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.kbtg.BackendExam.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
