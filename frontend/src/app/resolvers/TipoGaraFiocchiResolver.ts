import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs/Observable";
import {GareService} from "../gare.service";
import {TipoGara} from "../model/TipoGara";
@Injectable()
export class TipoGaraFiocchiResolver implements Resolve<TipoGara> {

  constructor(private gareService: GareService){

  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TipoGara> | Promise<TipoGara> | TipoGara {
    return this.gareService.getTipoFiocchi();
  }
}
