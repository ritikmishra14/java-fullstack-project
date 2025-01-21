import { validateHorizontalPosition } from '@angular/cdk/overlay';
import { CommonModule } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSnackBar } from '@angular/material/snack-bar';
import swal from 'sweetalert';
import { UserService } from '../../services/user.service';
@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [
    CommonModule,
        MatButtonModule,        
        MatFormFieldModule,
        MatInputModule,
        FormsModule,
       
  ],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent implements OnInit {

  
  constructor(
    private userService : UserService,
    private _snackBar : MatSnackBar
  ){}

  public user ={
    username : '',
    password: '',
    firstName: '',
    lastName: '',
    phone: '',
    email: '',
    profile: ''
    
  }
  ngOnInit(): void {
    
    
  }

  formSubmit(){
    if(this.user.username === '' || this.user.username === null){
      //console.log('username is required'); 
     // alert('username is required');
      this._snackBar.open('Username is required!' , '' ,
         {
          duration: 3000,
          verticalPosition:'top',
          horizontalPosition:'right'
          
           
        }
        
      );
      
      return;
    } 
    // adduser : userservice
    this.userService.addUser(this.user).subscribe(
      (data)=>{
      swal('success' , 'Registration successfull' , 'success');
       },
       (error)=>{
      //alert('error')
      this._snackBar.open('Error' , '' , {duration:3000});
      }
    )
    console.log(this.user);
    
  }
  


}
