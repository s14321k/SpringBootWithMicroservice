import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';

import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { LoginService } from './login/login.service';
import { RegisterComponent } from './register/register.component';
import { RegisterService } from './register/register.service';
import { FindFlightComponent } from './find-flight/find-flight.component';
import { AuthFormComponent } from './auth-form/auth-form.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NameFormComponent } from './name-form/name-form.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    FindFlightComponent,
    AuthFormComponent,
    NameFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgbModule
  ],
  providers: [LoginService, RegisterService],
  bootstrap: [AppComponent]
})
export class AppModule { }
