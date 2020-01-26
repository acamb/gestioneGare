import {Injectable} from "@angular/core";
import {AuthenticationService} from "../authentication.service";
import {ActivatedRouteSnapshot, CanActivate, CanActivateChild, Router, RouterStateSnapshot} from "@angular/router";
import {Observable} from "rxjs/Observable";

@Injectable()
export class AuthGuard implements CanActivate, CanActivateChild{

  constructor( private authService : AuthenticationService, private router : Router ) {
  }

  canActivate( route : ActivatedRouteSnapshot, state : RouterStateSnapshot ) {
    if(this.authService.authenticated) return true;
    else {
      this.authService.goToLogin()
      return false;
    }
  }


  canActivateChild(childRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    return this.canActivate(childRoute,state);
  }
}
