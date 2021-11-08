import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseURL = environment.baseURL
  private loginURL = this.baseURL + "auth/login"

  constructor(private http: HttpClient) {}

  public authenticate(login: string, password: string) {
    let res = this.http.post(this.loginURL, {login: login, password: password})

    console.log(res)
  }
}
