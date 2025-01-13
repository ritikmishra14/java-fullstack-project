import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [
    CommonModule,
        MatButtonModule,        
        MatFormFieldModule,
        MatInputModule,
        FormsModule
  ],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent implements OnInit {

  constructor(){}

  public user ={
    username : '',
    password: '',
    firstName: '',
    lastName: '',
    phone: '',
    email: ''
  }
  ngOnInit(): void {
    
    
  }

  formSubmit(){
    if(this.user.username === '' || this.user.username === null){
      alert('user is required');
    }
    console.log(this.user);
    
  }
  


}
