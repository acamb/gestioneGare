import { Directive, Input, ElementRef, ViewContainerRef, TemplateRef, OnInit } from '@angular/core';
import { Role } from '../model/Role';
import { AuthenticationService } from '../authentication.service';
import { User } from '../model/User';
import { Observable } from 'rxjs';

@Directive({
  selector: '[appRestrictedRoles]'
})
export class RestrictedRoleDirective implements OnInit{


  private roles: Array<string>;

  @Input()
  set appRestrictedRoles(roleList: Array<string>){
    this.roles=roleList;
  }

  constructor(private authService: AuthenticationService,
    private templateRef: TemplateRef<any>,
    private viewContainer: ViewContainerRef) {

    }

    ngOnInit(): void {
      this.authService.userObservable.subscribe(user => this.onUserChanged(user));
    }


    onUserChanged(user: User) {
      let roleFound = false;
    if (user && user.roles && this.roles){ //logged in, and roles configured
      user.roles.forEach(
        role => roleFound = roleFound || this.roles.findIndex((element) => element === role.name) != -1 );
      if ( roleFound ) {
        this.viewContainer.createEmbeddedView(this.templateRef);
      }
      else {
        this.viewContainer.clear();
      }
    }
    }
}
