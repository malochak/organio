import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {AuthService} from "../authentication/services/auth.service";

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.scss']
})
export class LandingComponent implements OnInit {

  constructor(private authService: AuthService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  async ngOnInit() {
    if (this.authService.checkAuthenticated()) {
      await this.router.navigate([this.route.snapshot.queryParams.returnUrl || "/dashboard"])
    }
  }

}
