import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {GareService} from "../../gare.service";
import {Subject} from "rxjs";
import {Gara} from "../../model/Gara";

@Component({
  selector: 'app-gara-picker',
  templateUrl: './gara-picker.component.html',
  styleUrls: ['./gara-picker.component.css']
})
export class GaraPickerComponent implements OnInit {

  annoSocietario: number;
  gare = new Subject<Array<Gara>>();
  @Output()
  garaSelected = new EventEmitter<Gara>();

  constructor(private gareService: GareService) {
    this.impostaAnno();
  }

  ngOnInit() {
  }

  onChangeAnno(){
    this.caricaGare(this.annoSocietario);
  }

  onGaraSelected(gara: Gara){
    this.garaSelected.next(gara);
  }

  async caricaGare(anno: number){
    let gare = await this.gareService.getGare(anno).toPromise();
    this.gare.next(gare);
  }

  async impostaAnno(){
    let anno = await this.gareService.getAnnoSocietario().toPromise();
    this.annoSocietario=anno;
    this.caricaGare(this.annoSocietario);
  }

}
