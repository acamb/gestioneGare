import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Arciere} from "../../model/Arciere";

@Component({
  selector: 'app-table-classifica',
  templateUrl: './table-classifica.component.html',
  styleUrls: ['./table-classifica.component.css']
})
export class TableClassificaComponent implements OnInit {

  gruppoVal: Array<Arciere>;

  @Input()
  get gruppo(){
    return this.gruppoVal;
  }

  @Output()
  gruppoChange = new EventEmitter();

  set gruppo(val){
    this.gruppoVal=val;
    this.gruppoChange.emit(this.gruppoVal)
  }

  @Input()
  descrizione: string = "";

  @Input()
  canModify: boolean = false;

  @Input()
  templatePunti : Array<string>;

  constructor() { }

  ngOnInit() {
  }

  propagate(arciere) {
    //devo ricalcolare il punteggio totale partendo dagli scomposti
    let punteggioString = "";
    for(let p of arciere.punteggi){
      punteggioString += "" + p.punteggio;
    }
    arciere.punteggio = parseInt(punteggioString.trim());
    this.gruppoChange.emit(this.gruppoVal);
  }

  //necessario per far usare l'indice all'ngFor invece che l'identity
  trackByIdx(index: number, obj: any): any {
    return index;
  }
}
