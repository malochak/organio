import {Component} from '@angular/core';
import {FormBuilder} from "@angular/forms";
import {AuthService} from "../../services/auth.service";

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

  authError: boolean = false;

  validationErrors: Map<string, Array<string>> = new Map()

  constructor(private fb: FormBuilder, private authService: AuthService) {
  }

  async onSubmit() {
    if (this.loginForm.valid) {
      this.authError = false
      let login = this.loginForm.controls.login.value as string
      let password = this.loginForm.controls.password.value as string
      await this.authService.authenticate(login, password)
    } else {
      this.authError = true
    }
  }


  validateRequiredFields() {
    let controls = this.loginForm.controls;
    for (let controlsKey in controls) {
      if(controls[controlsKey].hasError('required')) {
        this.validationErrors.set(controlsKey, [`${controlsKey} is required`])
      }

    }
    return true
  }

}
