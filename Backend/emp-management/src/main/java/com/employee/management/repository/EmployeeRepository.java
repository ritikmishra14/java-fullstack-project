package com.employee.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.employee.management.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

	// custom query I will write to find it.
	@Query("select emp from Employee emp where emp.employeeId = ?1")
	public Employee fetchItemById(String employeeId);

//	@Query("delete emp from Employee emp where emp.employeeId = ?1")
//	public void removeById(String employeeId);

}
