import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Employee } from '../model/employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  baseUrl: string = 'http://localhost:8080/api/v1/employees';
  // baseurlPost: string = 'http://localhost:8080/api/v1/employee';
  employees: Employee[] = [];
  constructor(private http: HttpClient) {
   }

//GET ALL EMPLOYEE
getAllEmployees(): Observable<Employee[]> {
  return this.http.get<Employee[]>(`${this.baseUrl}`)
}

// Create an employee
createEmployee(employee: Employee): Observable<Employee> {
  return this.http.post<Employee>(`${this.baseUrl}` , employee);
}

// GET Employee by Id:
getEmployeeById(employeeId: string): Observable<Employee>{
   return this.http.get<Employee>(`${this.baseUrl}/${employeeId}`);
}

// update employee
updateEmployee(employeeId: string , employee: Employee): Observable<Employee>{
  return this.http.put<Employee>(`${this.baseUrl}/${employeeId}` , employee)
}

// delete employee
deleteEmployee(employeeId: string): Observable<void>{
 return this.http.delete<void>(`${this.baseUrl}/${employeeId}`)
}
}
