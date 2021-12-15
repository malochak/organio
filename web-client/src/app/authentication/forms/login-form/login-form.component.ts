import {Component, OnInit} from '@angular/core';
import {FormBuilder} from "@angular/forms";
import {AuthService} from "../../services/auth.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.scss']
})
export class LoginFormComponent implements OnInit {
  loginForm = this.fb.group({
    login: '',
    password: ''
  })

  authError: boolean = false;
  returnUrl: string = "/dashboard"
  validationErrors: Map<string, Array<string>> = new Map()

  constructor(private fb: FormBuilder,
              private authService: AuthService,
              private route: ActivatedRoute,
              private router: Router) {
    this.returnUrl = this.route.snapshot.queryParams.returnUrl || '/dashboard';
  }

  async ngOnInit() {
    if (this.authService.checkAuthenticated()) {
      await this.router.navigate([this.returnUrl]);
    }
  }

  async onSubmit() {
    if (this.loginForm.valid) {
      this.authError = false
      const {login, password} = this.loginForm.value
      let loginResponse = await this.authService.authenticate(login, password)

      if (loginResponse.success) {
        await this.router.navigate([this.returnUrl])
      } else {
        // todo hannde errors
      }
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
