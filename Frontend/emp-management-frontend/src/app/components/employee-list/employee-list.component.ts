import { NgFor } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from '../../model/employee';
import { EmployeeService } from '../../services/employee.service';

@Component({
  selector: 'app-employee-list',
  standalone: true,
  imports: [NgFor],
  templateUrl: './employee-list.component.html',
  styleUrl: './employee-list.component.css'
})
export class EmployeeListComponent implements OnInit {

  employees: Employee[] = [];

  constructor(
    private employeeService: EmployeeService,
    private router: Router
  ){

  }
  ngOnInit(): void {
   this.getEmployees();
  }

  // get employees
  getEmployees(){
    this.employeeService.getAllEmployees()
    .subscribe((data)=>{
      this.employees = data;
    });
  }

  // update employee
  updateEmployee(employeeId: string){
  this.router.navigate(['update-employee' , employeeId]);
  }

  // delete Employee
  deleteEmployee(employeeId: string){
    this.employeeService.deleteEmployee(employeeId).subscribe
  ((data)=>{console.log('employee deleted');
  })
    this.router.navigate(['employees' , employeeId]);
  }

}
