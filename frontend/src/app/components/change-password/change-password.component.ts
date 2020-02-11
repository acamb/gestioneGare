import { Component, OnInit } from '@angular/core';
import {UsersService} from "../../users.service";
import {AuthenticationService} from "../../authentication.service";

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

   successInfo:boolean;
   error:string;
   oldPassword:string;
   newPassword:string;
   repeatNewPassword:string;

  constructor(private userService: UsersService,private authService: AuthenticationService ) { }

  ngOnInit() {
  }

  changePassword(){
    if(this.newPassword !== this.repeatNewPassword){
      this.error="Le password non corrispondono";
      return;
    }
    this.userService.updatePassword(this.authService.user,this.oldPassword,this.newPassword)
      .take(1)
      .subscribe(
      res => this.successInfo=true,
      error => {this.successInfo=false;this.error="La password attuale non corrisponde, password non aggiornata"}
    )
  }

}
