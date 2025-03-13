import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { EmployeeListComponent } from "./components/employee-list/employee-list.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet, 
    EmployeeListComponent ,
    RouterLink ,
    RouterOutlet
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'emp-management-frontend';
}
