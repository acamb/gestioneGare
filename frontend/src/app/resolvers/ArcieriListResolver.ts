import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {Arciere} from "../model/Arciere";
import {GareService} from "../gare.service";

@Injectable()
export class ArcieriListResolver implements Resolve<Array<Arciere>> {

  constructor(private gareService: GareService){

  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Array<Arciere>> | Promise<Array<Arciere>> | Array<Arciere> {
    return this.gareService.getArcieri();
  }
}
