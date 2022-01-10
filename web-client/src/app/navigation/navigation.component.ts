import {Component, Input} from '@angular/core';
import {NavItem} from "../domain/nav-item";
import {authenticatedNavItems} from "../constants/authenticated-nav-items";
import {unauthenticatedNavItems} from "../constants/unauthenticated-nav-items";
import {AuthService} from "../authentication/services/auth.service";
import {MatSidenav} from "@angular/material/sidenav";

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent {

  @Input() sidenav: MatSidenav

  isAuthenticated: boolean
  items: Array<NavItem>

  constructor(private authService: AuthService) {
    this.authService.isAuthenticated.subscribe(
      (isAuthenticated: boolean) => {
        this.isAuthenticated = isAuthenticated
        this.items = isAuthenticated ? authenticatedNavItems(this.authService) : unauthenticatedNavItems()
      }
    );
  }

}
