
import {take, map} from 'rxjs/operators';
import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {AuthenticationService} from "../../authentication.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  username: string;
  password: string;
  wrongLogin: boolean;

  constructor(private router: Router,private authService: AuthenticationService) { }

  ngOnInit() {
    if(this.authService.authenticated){
      this.router.navigateByUrl("/gestioneGare")
    }
  }

  onSubmit(){
    this.authService.authenticate(this.username,this.password).pipe(map(result => {
      if(result){
        this.router.navigateByUrl("/gestioneGare")
      }
      else{
        this.wrongLogin=true;
      }
    }),take(1),).subscribe();
  }

}
