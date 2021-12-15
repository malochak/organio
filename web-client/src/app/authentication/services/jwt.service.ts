import {Injectable} from '@angular/core';
import jwtDecode from "jwt-decode";
import {JWTConstants} from "../../constants/JWTConstants";
import * as moment from "moment";

@Injectable({
  providedIn: 'root'
})
export class JwtService {

  public resolveToken(token: string): void {
    const {exp, username} = jwtDecode<any>(token)
    let expiresAt = moment().add(exp)

    localStorage.setItem(JWTConstants.TOKEN_ID, token)
    localStorage.setItem(JWTConstants.TOKEN_EXP, JSON.stringify(expiresAt))
    localStorage.setItem(JWTConstants.TOKEN_USER, username)
  }

  public isTokenValid(): boolean {
    const token = localStorage.getItem(JWTConstants.TOKEN_ID)
    const exp = localStorage.getItem(JWTConstants.TOKEN_EXP) as string
    const expiresAt = JSON.parse(exp)

    let isValid = Boolean(token) && moment().isBefore(expiresAt);

    if (!isValid) {
      this.clearStorage();
      return false;
    }

    return true
  }

  public clearStorage(): void {
    localStorage.removeItem(JWTConstants.TOKEN_ID)
    localStorage.removeItem(JWTConstants.TOKEN_EXP)
    localStorage.removeItem(JWTConstants.TOKEN_USER)
  }
}
