import { Routes } from '@angular/router';
import { CreateEmployeeComponent } from './components/create-employee/create-employee.component';
import { EmployeeListComponent } from './components/employee-list/employee-list.component';
import { UpdateEmployeeComponent } from './components/update-employee/update-employee.component';

export const routes: Routes = [
  {
    path: 'employees',
    component: EmployeeListComponent
  },
  {
    path: '',
    redirectTo: 'employees',
    pathMatch: 'full'
  },
  {
    path: 'create-employee',
    component: CreateEmployeeComponent
  },
  {
    path: 'update-employee/:employeeId',
    component: UpdateEmployeeComponent
  }
];
