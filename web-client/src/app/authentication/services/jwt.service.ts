import {Injectable} from '@angular/core';
import jwtDecode from "jwt-decode";
import {JwtConstants} from "../../constants/jwt-constants";
import * as moment from "moment";

@Injectable({
  providedIn: 'root'
})
export class JwtService {

  public resolveToken(token: string): void {
    const {exp, username} = jwtDecode<any>(token)
    let expiresAt = moment().add(exp)

    localStorage.setItem(JwtConstants.TOKEN_ID, token)
    localStorage.setItem(JwtConstants.TOKEN_EXP, JSON.stringify(expiresAt))
    localStorage.setItem(JwtConstants.TOKEN_USER, username)
  }

  public isTokenValid(): boolean {
    const token = localStorage.getItem(JwtConstants.TOKEN_ID)
    const exp = localStorage.getItem(JwtConstants.TOKEN_EXP) as string
    const expiresAt = JSON.parse(exp)

    let isValid = Boolean(token) && moment().isBefore(expiresAt);

    if (!isValid) {
      this.clearStorage();
      return false;
    }

    return true
  }

  public clearStorage(): void {
    localStorage.removeItem(JwtConstants.TOKEN_ID)
    localStorage.removeItem(JwtConstants.TOKEN_EXP)
    localStorage.removeItem(JwtConstants.TOKEN_USER)
  }
}
