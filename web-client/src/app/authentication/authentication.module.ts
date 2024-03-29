import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {LoginComponent} from './login/login.component';
import {AuthenticationRoutingModule} from "./authentication-routing.module";
import {MatCardModule} from "@angular/material/card";
import {LoginFormComponent} from './forms/login-form/login-form.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {ReactiveFormsModule} from "@angular/forms";
import {MatButtonModule} from "@angular/material/button";
import {RegisterComponent} from './register/register.component';
import {RegisterFormComponent} from './forms/register-form/register-form.component';

@NgModule({
  declarations: [
    LoginComponent,
    LoginFormComponent,
    RegisterComponent,
    RegisterFormComponent
  ],
    imports: [
        CommonModule,
        AuthenticationRoutingModule,
        MatCardModule,
        MatFormFieldModule,
        MatInputModule,
        ReactiveFormsModule,
        MatButtonModule,
    ]
})
export class AuthenticationModule {
}
