import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LandingComponent} from "./landing/landing.component";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {AuthenticationModule} from "./authentication/authentication.module";
import {AuthGuardService} from "./services/auth-guard.service";

const routes: Routes = [
  {path: '', component: LandingComponent},
  {path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuardService]},
  {path: 'auth', loadChildren: () => AuthenticationModule}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
