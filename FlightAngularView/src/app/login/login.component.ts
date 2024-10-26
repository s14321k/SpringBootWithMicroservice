import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { LoginService } from './login.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'rxjs';
import { find, takeUntil } from 'rxjs/operators'

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit, OnDestroy {

  loginH2: string = "Login";

  loginForm!: FormGroup;

  private unsubscribe$ = new Subject<void>;

  constructor(private fb: FormBuilder, private loginAuthService : LoginService, private router : Router, private route : ActivatedRoute){}

  ngOnInit(): void 
  {
    this.loginForm = this.fb.group({
      email    : ['', [Validators.required]],
      password : ['', [Validators.required]]
    })

    this.route.queryParams.subscribe(params => {
      this.loginH2 = params['loginH2'] || ['Login'];
    })
  }

  onSubmit(): void
  {
    if(this.loginForm.valid)
    {
      const email    = this.loginForm.get('email')?.value;
      const password = this.loginForm.get('password')?.value;


      this.loginAuthService.authenticate(email, password)
                           .pipe(takeUntil(this.unsubscribe$))
                           .subscribe(
                            {
                              next: (isAuthenticated) => {
                                                            if(isAuthenticated)
                                                            {
                                                              console.log("User Exists")
                                                              this.router.navigate(['find-flight']);
                                                            }
                                                            else
                                                            {
                                                              console.log("Not Exists")
                                                            }
                                                         },
                              complete: () =>  console.info("Authentication Complete"), 
                              error: (error) => console.error("Authentication failed : "+error)
                            });


      // this.loginAuthService.authenticate(email, password)
      //                     .subscribe((isAuthenticated) => {
      //                                                       console.log(isAuthenticated + " : User Exist");
      //                                                     },
      //                                                     (error) => 
      //                                                     {
      //                                                       console.log(error + " : Couldn't save")
      //                                                     });

    }
  }

  ngOnDestroy(): void
  {
      this.unsubscribe$.next();
      
  }

  goToRegister(): void{
    this.router.navigate(['register']);
  }
}
