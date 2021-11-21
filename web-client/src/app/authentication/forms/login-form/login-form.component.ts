import {Component} from '@angular/core';
import {FormBuilder} from "@angular/forms";

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.scss']
})
export class LoginFormComponent {
  loginForm = this.fb.group({
    login: '',
    password: ''
  })
  constructor(private fb: FormBuilder) { }

  onSubmit() {
    console.log(this.loginForm)

  }

}
