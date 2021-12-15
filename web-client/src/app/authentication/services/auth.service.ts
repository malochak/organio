import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {BehaviorSubject} from "rxjs";
import {LoginResponse} from "../payload/LoginResponse";
import {JwtService} from "./jwt.service";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseURL: string = environment.baseURL
  private loginURL: string = this.baseURL + "auth/login"

  public isAuthenticated: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false)

  constructor(private http: HttpClient, private jwt: JwtService) {
  }

  public authenticate = async (login: string, password: string): Promise<LoginResponse> =>
    this.http.post<any>(this.loginURL, {username: login, password: password})
      .toPromise()
      .then(res => {
        this.isAuthenticated.next(true)
        this.jwt.resolveToken(res.token)
        return {
          success: true,
          errors: {field: "", errors: []}
        }
      })
      .catch(err => {
        this.isAuthenticated.next(false)
        this.jwt.clearStorage()
        return {
          success: false,
          errors: {field: "", errors: []}
        }
      })

    checkAuthenticated(): boolean {
      let isTokenValid = this.jwt.isTokenValid()
      this.isAuthenticated.next(isTokenValid)
      return isTokenValid;
    }
}
