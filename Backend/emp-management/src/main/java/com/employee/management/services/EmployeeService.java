package com.employee.management.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.employee.management.exceptions.ResourceNotFoundException;
import com.employee.management.model.Employee;
import com.employee.management.repository.EmployeeRepository;

@Service
public class EmployeeService {

	private EmployeeRepository employeeRepository;

	// constructor injection. by default it wires our dependency.
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	// GET All Employees
	public List<Employee> getAllEmployees() {
		List<Employee> allEmps = this.employeeRepository.findAll();
		if (allEmps.size() <= 0) {
			throw new ResourceNotFoundException(" no employees present");
		}
		return allEmps;
	}

	// GET employee by id
	public Employee getEmployeeById(String id) {
		Employee emp = this.employeeRepository.fetchItemById(id);
		return emp;
	}

	// Create Employee
	public Employee createEmployee(Employee employee) {
		Employee newEmployee = this.employeeRepository.save(employee);
		return newEmployee;
	}

	// Update Employee
	public Employee updateEmployee(String id, Employee employee) {
		Employee previousEmployee = getEmployeeById(id);
		previousEmployee.setEmployeeId(employee.getEmployeeId());
		previousEmployee.setFirstName(employee.getFirstName());
		previousEmployee.setLastName(employee.getLastName());
		previousEmployee.setEmail(employee.getEmail());
		// save the updated previous employee
		Employee updatedEmployee = this.createEmployee(previousEmployee);
		return updatedEmployee;
	}

	// delete employee by id
	public void deleteById(String employeeId) {
		this.employeeRepository.deleteById(employeeId);
	}
}
