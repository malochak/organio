import {Component} from '@angular/core';
import {NavItem} from "../domain/nav-item";
import {authenticatedNavItems} from "../constants/authenticated-nav-items";
import {unauthenticatedNavItems} from "../constants/unauthenticated-nav-items";
import {AuthService} from "../authentication/services/auth.service";

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent {
  isAuthenticated: boolean;
  items: Array<NavItem>;

  constructor(private authService: AuthService) {
    this.authService.isAuthenticated.subscribe(
      (isAuthenticated: boolean) => {
        this.isAuthenticated = isAuthenticated
        this.items = isAuthenticated ? authenticatedNavItems(this.authService) : unauthenticatedNavItems()
      }
    );
  }

}
