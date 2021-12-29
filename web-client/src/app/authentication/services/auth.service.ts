import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {BehaviorSubject} from "rxjs";
import {LoginResponse} from "../payload/LoginResponse";
import {JwtService} from "./jwt.service";
import {FieldsErrors} from "../../utils/FieldsErrors";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private authURL: string = environment.baseURL + "/auth"
  private loginURL: string = this.authURL + "/login"
  private logoutURL: string = this.authURL + "/logout" // todo: implement logout action

  public isAuthenticated: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false)

  constructor(private http: HttpClient, private jwt: JwtService) {
    this.isAuthenticated.next(this.checkAuthenticated())
  }

  public authenticate = async (login: string, password: string): Promise<LoginResponse> =>
    this.http.post<any>(this.loginURL, {login: login, password: password})
      .toPromise()
      .then(res => {
        this.isAuthenticated.next(true)
        this.jwt.resolveToken(res.token)
        return {
          success: true,
          errors: []
        }
      })
      .catch(err => {
        console.log(err.error.subErrors)
        console.log(err)
        this.isAuthenticated.next(false)
        this.jwt.clearStorage()
        return {
          success: false,
          errors: this.mapErrorResponseToFields(err)
        }
      })

  private mapErrorResponseToFields = (errorResponse: any): Array<FieldsErrors> => {
    return errorResponse.error.subErrors.map((err: any) => ({field: err.field, message: err.message}))
  }

  public checkAuthenticated = (): boolean => {
    let isTokenValid = this.jwt.isTokenValid()
    this.isAuthenticated.next(isTokenValid)
    return isTokenValid;
  }

  public logout = async (): Promise<void> => {
    this.jwt.clearStorage()
    this.isAuthenticated.next(false)
  }
}
