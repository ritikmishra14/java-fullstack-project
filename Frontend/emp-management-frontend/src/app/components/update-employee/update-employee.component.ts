import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Employee } from '../../model/employee';
import { EmployeeService } from '../../services/employee.service';

@Component({
  selector: 'app-update-employee',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './update-employee.component.html',
  styleUrl: './update-employee.component.css'
})
export class UpdateEmployeeComponent  implements OnInit{
  
  employeeId: string | undefined;
  employee: Employee = new Employee();
  constructor(
    private employeeService: EmployeeService ,
    private activatedRoute: ActivatedRoute ,
    private router: Router,
  ){

  }
  ngOnInit(): void {
   this.employeeId = this.activatedRoute.snapshot.params['employeeId'];
   console.log(this.employeeId);
   
    this.getEmployeeById(this.employeeId ?? '');
  }

  // get employee by id and populate here
 getEmployeeById(employeeId: string){
  this.employeeService.getEmployeeById(employeeId)
  .subscribe((data)=>{this.employee = data}); 
 }

 // save updated employee
 saveUpdatedEmployee(){
 this.employeeService.updateEmployee(this.employeeId ?? '' , this.employee)
 }

 gotToEmployeeList(){
  this.router.navigate(['/employees']);
}


  onSubmit(){
    this.employeeService.updateEmployee(this.employeeId ?? '' , this.employee)
    .subscribe((data)=>{
      this.gotToEmployeeList();
    })
  }
}
