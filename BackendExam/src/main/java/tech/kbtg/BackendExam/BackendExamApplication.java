package tech.kbtg.BackendExam;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tech.kbtg.BackendExam.service.EmployeeService;
import tech.kbtg.BackendExam.entity.Employee;

@SpringBootApplication
public class BackendExamApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendExamApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner (EmployeeService employeeService)
	{
		return  runner -> {
			// Creat table
			CreateTable(employeeService);
		};
	}

	private void CreateTable(EmployeeService employeeService)
	{
		Employee employee = new Employee("Adam", "SerAdam",
				"Nickdam", 20000,"Bangkok", "Current", "PositionA");
		employeeService.save(employee);
		System.out.println("Success Initialize");
	}
}
