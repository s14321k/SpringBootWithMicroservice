import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms'
import { RegisterService } from './register.service';
import { Observable, of } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit{

  registerFromGroup !: FormGroup;

  constructor(private fb : FormBuilder, private registerService: RegisterService, private router: Router){};

  //Validation to check the password is match. Import AbstractControl, ValidationErrors, ValidatorFn from lib
  confirmPasswordValidator: ValidatorFn = (control: AbstractControl): ValidationErrors | null => 
  {
    console.log(control.value.password);
    console.log(control.value.confirmPas);
      if(control.value.password != control.value.confirmPas)
      { 
        console.log("no match");
        return of('PasswordNoMatch');
      }
      return of(null);
  };

  ngOnInit(): void 
  {
      this.registerFromGroup = this.fb.group({
        firstName : ['', [Validators.required]],
        lastName  : ['', [Validators.required]],
        email     : ['', [Validators.required]],
        password  : ['', [Validators.required]],
        confirmPas: ['', [Validators.required], this.confirmPasswordValidator]
      });
  }

  onchange(): void
  {
    this.confirmPasswordValidator;
  }

  onSubmit(): void
  {
    if(this.registerFromGroup.valid)
    {
      const firstName = this.registerFromGroup.get("firstName")?.value;
      const lastName  = this.registerFromGroup.get("lastName")?.value;
      const email     = this.registerFromGroup.get("email")?.value;
      const password  = this.registerFromGroup.get("password")?.value;
      
      this.registerService.registerUser(firstName, lastName, email, password)
                          .subscribe({
                            next: (reponse: string) => {
                              console.log("Next Response" + reponse);
                              this.router.navigate(['login'], {queryParams:{loginH2 : 'Registration Successful! Yoou can now login ' + reponse}});
                            },
                            complete: console.info, 
                            error: console.error
                          });
    }
  }

  goToLogin(): void{
    this.router.navigate(['login']);
  }
}
