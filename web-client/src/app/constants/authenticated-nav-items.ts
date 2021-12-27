import {AuthService} from "../authentication/services/auth.service";
import {NavItem} from "../domain/nav-item";

export function authenticatedNavItems(authService: AuthService): Array<NavItem> {
  return [
    {
      displayName: "Logout",
      matIcon: "logout",
      fullPath: "/",
      ariaLabel: "Logout button, it allows to go to logout from current account",
      action: () => {
        authService.logout()
      }
    }
  ];
}
