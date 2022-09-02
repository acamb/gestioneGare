import {Injectable} from '@angular/core';
import {AuthenticationService} from '../authentication.service';
import {ActivatedRouteSnapshot, CanActivate, CanActivateChild, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';

@Injectable()
export class AuthGuard implements CanActivate, CanActivateChild {

  constructor( private authService: AuthenticationService, private router: Router ) {
  }

  canActivate( route: ActivatedRouteSnapshot, state: RouterStateSnapshot ) {
    if ( this.authService.authenticated ) {
      if (route.data.roles === undefined) {
        return true;
      }
      else {
        let roleFound = false;
        this.authService.user.roles.forEach(role => roleFound = roleFound || route.data.roles.indexOf(role.name) !== -1 );
        return roleFound;
      }
    }
    else {
      this.authService.logout();
      return false;
    }
  }


  canActivateChild(childRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    return this.canActivate(childRoute, state);
  }
}
