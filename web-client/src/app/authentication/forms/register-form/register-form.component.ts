import {Component} from '@angular/core';
import {AuthenticationFormComponent} from "../authentication-form.component";
import {FormBuilder} from "@angular/forms";
import {AuthService} from "../../services/auth.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['../authentication-form.component.scss']
})
export class RegisterFormComponent extends AuthenticationFormComponent {

  constructor(protected fb: FormBuilder,
              protected authService: AuthService,
              protected route: ActivatedRoute,
              protected router: Router) {
    super(fb, authService, route, router);
    this.form = fb.group({
      login: '',
      password: '',
      passwordConfirmation: ''
    })
  }

  onSubmit = () => {
    console.log('onSubmit()')
  }
}
