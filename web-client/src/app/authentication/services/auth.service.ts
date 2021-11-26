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

  async authenticate(login: string, password: string): Promise<any> {
    console.log(this.loginURL)
    let response = this.http.post(this.loginURL, {username: login, password: password}).toPromise()
    response.then(res => console.log('res -', res)).catch(err => console.log('err -', err))
    console.debug(response)
    return response
  }
}
