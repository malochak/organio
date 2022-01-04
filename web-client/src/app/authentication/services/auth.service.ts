import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {BehaviorSubject} from "rxjs";
import {errorResponse, Response, successResponse} from "../payload/Response";
import {JwtService} from "./jwt.service";
import {LoginRequest} from "../payload/LoginRequest";
import {RegisterRequest} from "../payload/RegisterRequest";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private authURL: string = environment.baseURL + "/auth"
  private loginURL: string = this.authURL + "/login"
  private registerURL: string = this.authURL + "/register"
  private logoutURL: string = this.authURL + "/logout" // todo: implement logout action

  public isAuthenticated: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false)

  constructor(private http: HttpClient, private jwt: JwtService) {
    this.isAuthenticated.next(this.checkAuthenticated())
  }

  public authenticate = async (payload: LoginRequest): Promise<Response> =>
    this.http.post<any>(this.loginURL, payload)
      .toPromise()
      .then(res => {
        this.isAuthenticated.next(true)
        this.jwt.resolveToken(res.token)
        return successResponse
      })
      .catch(err => {
        this.isAuthenticated.next(false)
        this.jwt.clearStorage()
        return errorResponse(err)
      })

  public register = async (payload: RegisterRequest): Promise<Response> =>
    this.http.post(this.registerURL, payload)
      .toPromise()
      .then(() => successResponse)
      .catch(err => errorResponse(err))

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
