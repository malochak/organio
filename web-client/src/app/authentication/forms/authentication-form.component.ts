import {Directive, OnInit} from "@angular/core";
import {AuthService} from "../services/auth.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {FieldsErrors} from "../../utils/FieldsErrors";

@Directive()
export class AuthenticationFormComponent implements OnInit {

  form: FormGroup
  returnUrl: string = "/dashboard"

  constructor(protected fb: FormBuilder,
              protected authService: AuthService,
              protected route: ActivatedRoute,
              protected router: Router) {
    this.returnUrl = this.route.snapshot.queryParams.returnUrl || '/dashboard'
  }

  async ngOnInit() {
    if (this.authService.checkAuthenticated()) {
      await this.router.navigate([this.returnUrl])
    }
  }

  processErrorResponse = (fieldsErrors: Array<FieldsErrors>) => {
    fieldsErrors.forEach(fe => {
      const controls = this.form.controls
      controls[fe.field].setErrors({error: fe.message})
    })
  }

  getErrorMessage = (controlName: string) => {
    return this.form.controls[controlName].getError('error')
  }
}
