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

  constructor() { }

  ngOnInit() {
  }

  propagate() {
    this.gruppoChange.emit(this.gruppoVal);
  }
}
