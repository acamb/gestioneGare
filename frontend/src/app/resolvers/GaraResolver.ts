import {Gara} from "../model/Gara";
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import {Observable} from "rxjs";
import {GareService} from "../gare.service";
import {Injectable} from "@angular/core";
@Injectable()
export class GaraResolver implements Resolve<Gara>{

  constructor(private gareService: GareService){

  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Gara> | Promise<Gara> | Gara {
    let id = route.paramMap.get('id');
    return this.gareService.getGara({...new Gara(),id: +id});
  }
}
