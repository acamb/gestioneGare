import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "./model/User";
import {getServer} from "./Utils";
import {Observable} from "rxjs";

@Injectable()
export class UsersService {

  constructor(private httpClient: HttpClient) { }

  updateUser(user: User): Observable<User>{
    return this.httpClient.put<User>(getServer() + "users/update",user)
  }

  listUsers(): Observable<Array<User>>{
    return this.httpClient.get<Array<User>>(getServer()+"users/list");
  }

  updatePassword(user: User,oldPassword: string,newPassword: string): Observable<User>{
    return this.httpClient.put<User>(getServer()+"users/password",{user: user,oldPassword:oldPassword,newPassword: newPassword});
  }

}
