
import {map} from 'rxjs/operators';
import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {GareService} from './gare.service';
import {Observable,  BehaviorSubject } from 'rxjs';
import {Router} from '@angular/router';
import { User } from './model/User';
import { getServer } from './Utils';
import * as jwt_decode from 'jwt-decode'


@Injectable()
export class AuthenticationService {

  private subject = new BehaviorSubject<User>(undefined);

  get user(): User {
    return JSON.parse( sessionStorage.getItem('user') );
  }

  get userObservable(): Observable<User> {
    return this.subject.asObservable();
  }


  get authenticated(): boolean {
    const loggedIn = sessionStorage.getItem('user') !== undefined;
    if (!loggedIn) {
      return false;
    }
    const date = new Date(0);
    try {
      const exp = jwt_decode(sessionStorage.getItem('token')).exp;
      date.setUTCSeconds(exp);
      const valid = new Date().valueOf() < date.valueOf()
      return valid;
    }
    catch(error){
      return false;
    }
  }

  get token(){
    return this.authenticated ? sessionStorage.getItem('token') : undefined;
  }

  logout(){
    sessionStorage.removeItem('user');
    sessionStorage.removeItem('token');
    this.subject.next(undefined);
    this.goToLogin();
  }



  constructor(private httpClient: HttpClient, private router: Router) {
    this.subject.next(JSON.parse( sessionStorage.getItem('user')));
   }

  authenticate(username: string, password: string): Observable<boolean>{
    return this.httpClient.post<AuthResponse>(getServer() + 'auth', {username: username, password: password}).pipe(
      map(resp => {
        if (resp.token === undefined){
          return false;
        }
        sessionStorage.setItem('user', JSON.stringify(resp.user));
        sessionStorage.setItem('token', 'Bearer ' + resp.token);
        this.subject.next(resp.user);
        return true;
      },error => {
        return false;
      }))
  }

  goToLogin() {
    this.router.navigateByUrl('/authenticate');
  }
}

interface AuthResponse {
  token: string;
  user: User;
}
