package com.employee.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.management.exceptions.ResourceNotFoundException;
import com.employee.management.model.Employee;
import com.employee.management.services.EmployeeService;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

	@Autowired
	public EmployeeService employeeService;

	// Get all employees
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmps() {
		List<Employee> allEmps = this.employeeService.getAllEmployees();
		if (allEmps.size() <= 0) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(allEmps);
	}

	// Create Employee
	@PostMapping(value = "/employees")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		Employee newEmp = this.employeeService.createEmployee(employee);
		if (newEmp != null) {
			return ResponseEntity.ok(newEmp);
		}
		return ResponseEntity.badRequest().build();
	}

	// Get employee by id
	@GetMapping(value = "/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable String id) {
		Employee employee = this.employeeService.getEmployeeById(id);
		if (employee == null) {
			throw new ResourceNotFoundException("no employee with given " + id + " present");
		}
		return ResponseEntity.ok(employee);
	}

	// Update employee rest api
	@PutMapping(value = "/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable String id, @RequestBody Employee employee) {
		Employee updatedEmployee = this.employeeService.updateEmployee(id, employee);
		if (updatedEmployee == null) {
			throw new ResourceNotFoundException("employee does not exist");
		}
		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping("/employees/{employeeId}")
	@CrossOrigin(origins = "http://localhost:4200")
	public void deleteEmployeeById(@PathVariable String employeeId) {
		this.employeeService.deleteById(employeeId);
	}
}
