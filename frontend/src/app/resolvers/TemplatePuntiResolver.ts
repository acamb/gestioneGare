import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {Gara} from "../model/Gara";
import {GareService} from "../gare.service";
@Injectable()
export class TemplatePuntiResolver implements Resolve<Array<string>>{

  constructor(private gareService: GareService){

  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Array<string>> | Promise<Array<string>> | Array<string> {
    let id = route.paramMap.get('id');
    return this.gareService.getTemplatePunti({...new Gara(),id: +id});
  }
}
