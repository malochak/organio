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

  returnUrl: string = "/dashboard"

  constructor(private fb: FormBuilder,
              private authService: AuthService,
              private route: ActivatedRoute,
              private router: Router) {
    this.returnUrl = this.route.snapshot.queryParams.returnUrl || '/dashboard'
  }

  async ngOnInit() {
    if (this.authService.checkAuthenticated()) {
      await this.router.navigate([this.returnUrl])
    }
  }

  async onSubmit() {
    const {login, password} = this.loginForm.value
    let loginResponse = await this.authService.authenticate(login, password)

    if (loginResponse.success) {
      await this.router.navigate([this.returnUrl])
    } else {
      loginResponse.errors.forEach(fe => {
        const controls = this.loginForm.controls
        controls[fe.field].setErrors({error: fe.message})
      })
    }
  }

  getErrorMessage(controlName: string): string {
    return this.loginForm.controls[controlName].getError('error')
  }
}
