import { Component, OnInit } from '@angular/core';
import {Divisione} from "../../model/Divisione";
import {Observable} from "rxjs";
import {GareService} from "../../gare.service";

@Component({
  selector: 'app-gestione-divisioni',
  templateUrl: './gestione-divisioni.component.html',
  styleUrls: ['./gestione-divisioni.component.css']
})
export class GestioneDivisioniComponent implements OnInit {

  divisioni: Observable<Array<Divisione>>;
  nuovaDivisione = new Divisione();
  headers={descrizione: "Descrizione",defaultGroup : "Default"}

  constructor(private gareService: GareService) {
    this.divisioni = gareService.getDivisioni();
  }

  ngOnInit() {

  }

  async saveDivisione(){
    await this.gareService.insertDivisione(this.nuovaDivisione).toPromise();
    this.divisioni = this.gareService.getDivisioni();
    this.nuovaDivisione=new Divisione();
  }

  async deleteDivisione(divisione: Divisione){
    console.log("delete: " + JSON.stringify(divisione));
    await this.gareService.deleteDivisione(divisione).toPromise();
    this.divisioni = this.gareService.getDivisioni();
  }

  async updateDivisione(divisione: Divisione){
    divisione.defaultGroup = !divisione.defaultGroup;
    await this.gareService.updateDivisione(divisione).toPromise();
    this.divisioni = this.gareService.getDivisioni();
  }

}
