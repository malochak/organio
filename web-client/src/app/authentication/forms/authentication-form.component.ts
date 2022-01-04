import {Directive, OnInit} from "@angular/core";
import {AuthService} from "../services/auth.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {FieldsErrors} from "../../utils/FieldsErrors";
import {Response} from "../payload/Response";

@Directive()
export class AuthenticationFormComponent implements OnInit {

  form: FormGroup

  constructor(protected fb: FormBuilder,
              protected authService: AuthService,
              protected route: ActivatedRoute,
              protected router: Router,
              protected successURL: string) {
    this.successURL = this.route.snapshot.queryParams.returnUrl || successURL
  }

  async ngOnInit() {
    if (this.authService.checkAuthenticated()) {
      await this.router.navigate([this.route.snapshot.queryParams.returnUrl || "/dashboard"])
    }
  }

  evalResponse = async (response: Response) => {
    if (response.success) {
      await this.router.navigate([this.successURL])
    } else {
      this.processErrorResponse(response.errors)
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
