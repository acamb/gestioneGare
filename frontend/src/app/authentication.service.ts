import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {GareService} from "./gare.service";
import {extractData} from "./app.module";
import {Observable} from "rxjs/Observable";
import {Router} from "@angular/router";

@Injectable()
export class AuthenticationService {


  get authenticated() {
    return sessionStorage.getItem("user") != undefined;
  }

  get token(){
    return this.authenticated ? sessionStorage.getItem("token") : undefined
  }



  constructor(private httpClient: HttpClient,private router: Router) { }

  authenticate(username: string,password: string): Observable<boolean>{
    return this.httpClient.post<AuthResponse>(GareService.getServer() +"auth",{username:username,password:password})
      .map(user => {
        if(user.token === undefined){
          return false;
        }
        sessionStorage.setItem("user",username)
        sessionStorage.setItem("token","Bearer " + user.token)
        return true;
      })
  }

  goToLogin() {
    this.router.navigateByUrl("/authenticate")
  }
}

interface AuthResponse {
  token:string;
}
