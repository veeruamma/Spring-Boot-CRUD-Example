package practice.crud.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import practice.crud.example.entity.Employee;
import practice.crud.example.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;

	// get all employees api
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	// create employee
	@PostMapping("/employees")
	public Employee ceateEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}

	// get employee by id
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(name = "id") long employeeId) {
		Employee employee = employeeRepository.getById(employeeId);
		return ResponseEntity.ok().body(employee);

	}

	// update employee
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(name = "id") long employeeId, @RequestBody Employee employeeDetails) {
		Employee employee = employeeRepository.getById(employeeId);
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailId(employeeDetails.getEmailId());
		employeeRepository.save(employee);
		
		return ResponseEntity.ok().body(employee);
		
	}

	// delete employee by id
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable(name = "id") long employeeId) {
//		Employee employee = employeeRepository.getById(employeeId);
		employeeRepository.deleteById(employeeId);
		return ResponseEntity.ok().build();
	}

}
