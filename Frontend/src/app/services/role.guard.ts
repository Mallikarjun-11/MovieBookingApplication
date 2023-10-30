import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable, map } from 'rxjs';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {
  loggedIn=false;
  isAdmin;
  constructor(private loginService:LoginService, private router:Router){

  }
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    this.loggedIn = this.loginService.isLoggedIn();
  
    if (this.loggedIn) {
      return this.loginService.isAdmin(localStorage.getItem('username')).pipe(
        map((isAdmin) => {
          if (isAdmin) {
            return true; // User is an admin, allow access
          } else {
            this.router.navigate(['/']);
            return false; // User is not an admin, deny access
            
          }
        })
      );
    } else {
      this.router.navigate(['/']);
      return false; // User is not logged in, deny access
    }
  }
}
