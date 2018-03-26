import {Gara} from "../model/Gara";
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import {Observable} from "rxjs/Observable";
import {GareService} from "../gare.service";
import {toNumber} from "ngx-bootstrap/timepicker/timepicker.utils";
import {Injectable} from "@angular/core";
@Injectable()
export class GaraResolver implements Resolve<Gara>{

  constructor(private gareService: GareService){

  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Gara> | Promise<Gara> | Gara {
    let id = route.paramMap.get('id');
    return this.gareService.getGara({...new Gara(),id: toNumber(id)});
  }
}
