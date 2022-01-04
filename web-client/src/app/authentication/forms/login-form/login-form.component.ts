import {Component} from '@angular/core';
import {AuthenticationFormComponent} from "../authentication-form.component";
import {FormBuilder} from "@angular/forms";
import {AuthService} from "../../services/auth.service";
import {ActivatedRoute, Router} from "@angular/router";
import {LoginRequest} from "../../payload/LoginRequest";

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['../authentication-form.component.scss']
})
export class LoginFormComponent extends AuthenticationFormComponent {

  constructor(protected fb: FormBuilder,
              protected authService: AuthService,
              protected route: ActivatedRoute,
              protected router: Router) {
    super(fb, authService, route, router, "/dashboard");
    this.form = fb.group({
      login: '',
      password: ''
    })
  }

  onSubmit = async () => {
    const payload: LoginRequest = this.form.value
    let response = await this.authService.authenticate(payload)
    await this.evalResponse(response)
  }
}
