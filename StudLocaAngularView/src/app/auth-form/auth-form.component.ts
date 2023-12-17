import { Component, Input } from '@angular/core';
import  { FormBuilder, FormGroup, Validators } from '@angular/forms'

@Component({
  selector: 'app-auth-form',
  templateUrl: './auth-form.component.html',
  styleUrls: ['./auth-form.component.scss']
})
export class AuthFormComponent 
{
  isEmailFieldFocused =false;
  isPasswordFieldFocused = false;

  authFormGroup !: FormGroup;
 
  

  constructor(private fb : FormBuilder)
  {
    this.authFormGroup = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]]
    })
  }


  get email() {
    return this.authFormGroup.get('email');
  }

  get password() {
    return this.authFormGroup.get('password');
  }
}
