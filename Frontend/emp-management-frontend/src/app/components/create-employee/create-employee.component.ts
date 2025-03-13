import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Employee } from '../../model/employee';
import { EmployeeService } from '../../services/employee.service';

@Component({
  selector: 'app-create-employee',
  standalone: true,
  imports: [FormsModule ],
  templateUrl: './create-employee.component.html',
  styleUrl: './create-employee.component.css'
})
export class CreateEmployeeComponent implements OnInit {

  employee: Employee = new Employee();
  constructor(
    private employeeService: EmployeeService,
      private router: Router
  ){

  }
  ngOnInit(): void {
   
  }

  // Method calls starts here.

  // SAVE Employee
  saveEmployee(){
  this.employeeService.createEmployee(this.employee)
  .subscribe((data)=>{
    this.gotToEmployeeList();
  },
  (error)=>{console.log(error);
  }
) }

// Navigate to employee list
  gotToEmployeeList(){
    this.router.navigate(['/employees']);
  }

  onSubmit(){  
    this.saveEmployee();
  }
}
