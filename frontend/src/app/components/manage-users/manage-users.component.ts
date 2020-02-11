import { Component, OnInit } from '@angular/core';
import {User} from "../../model/User";
import {UsersService} from "../../users.service";

@Component({
  selector: 'app-manage-users',
  templateUrl: './manage-users.component.html',
  styleUrls: ['./manage-users.component.css']
})
export class ManageUsersComponent implements OnInit {

  private users:Array<User>;
  private resetPasswordUser: string;

  constructor(private userService: UsersService) { }

  private headers={username:"username",active:"attivo"};

  ngOnInit() {
    this.getUsers();
  }

  async getUsers(){
    this.users = await this.userService.listUsers().toPromise();
  }


  async toggleEnable(user: User){
    user.active = !user.active;
    await this.userService.updateUser(user).toPromise();
    this.getUsers();
  }

  async resetPassword(user: User){
    await this.userService.updatePassword(user,null,user.username).toPromise(); //reset the password to the username value
    this.resetPasswordUser=user.username;
  }

  getEnableButtonText(user: User){
    if(user.active) return "Disabilita";
    else return "Abilita";
  }
}
