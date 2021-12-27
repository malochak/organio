import {NavItem} from "../domain/nav-item";

export function unauthenticatedNavItems(): Array<NavItem> {
  return [
    {
      displayName: "Login",
      matIcon: "login",
      fullPath: "auth/login",
      ariaLabel: "Login button, it allows to go to login view",
      action: () => {}
    }
  ];
}

