import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {LoginComponent} from './login/login.component';
import {AuthenticationRoutingModule} from "./authentication-routing.module";
import {MatCardModule} from "@angular/material/card";


@NgModule({
  declarations: [
    LoginComponent
  ],
  imports: [
    CommonModule,
    AuthenticationRoutingModule,
    MatCardModule,
  ]
})
export class AuthenticationModule {
}
