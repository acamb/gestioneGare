import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {GareService} from './gare.service';
import {Observable} from 'rxjs/Observable';
import {Router} from '@angular/router';
import { User } from './model/User';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';

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
    return sessionStorage.getItem('user') != undefined;
  }

  get token(){
    return this.authenticated ? sessionStorage.getItem('token') : undefined;
  }



  constructor(private httpClient: HttpClient, private router: Router) {
    this.subject.next(JSON.parse( sessionStorage.getItem('user')));
   }

  authenticate(username: string, password: string): Observable<boolean>{
    return this.httpClient.post<AuthResponse>(GareService.getServer() + 'auth', {username: username, password: password})
      .map(resp => {
        if (resp.token === undefined){
          return false;
        }
        sessionStorage.setItem('user', JSON.stringify(resp.user));
        sessionStorage.setItem('token', 'Bearer ' + resp.token);
        this.subject.next(resp.user);
        return true;
      })
  }

  goToLogin() {
    this.router.navigateByUrl('/authenticate');
  }
}

interface AuthResponse {
  token: string;
  user: User;
}
