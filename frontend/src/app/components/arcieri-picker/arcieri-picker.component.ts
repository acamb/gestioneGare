import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Arciere} from "../../model/Arciere";
import {Subject} from "rxjs/Subject";
import {TipoGara} from "../../model/TipoGara";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-arcieri-picker',
  templateUrl: './arcieri-picker.component.html',
  styleUrls: ['./arcieri-picker.component.css']
})
export class ArcieriPickerComponent implements OnInit {

  @Input()
  arcieri : Array<Arciere>;
  @Input()
  canBeSelectedFunction = ()=>{return true;}
  @Input()
  numGruppi = 1
  @Output()
  addToA1 = new EventEmitter<Arciere>();
  @Output()
  addToA2 = new EventEmitter<Arciere>();
  @Output()
  addToB1 = new EventEmitter<Arciere>();
  @Output()
  addToB2 = new EventEmitter<Arciere>();

  arcieriFiltered : Array<Arciere> = [];
  arcieriFilter=undefined;
  arcieriSubject  = new Subject<Array<Arciere>>();

  constructor(private translate: TranslateService) { }

  ngOnInit(): void {
    setTimeout( () => {
      this.arcieriFiltered=this.arcieri;
      this.arcieriSubject.next(this.arcieriFiltered);
    },200);
  }

  addA1(arciere: Arciere){
    this.addToA1.emit(arciere);
  }
  addA2(arciere: Arciere){
    this.addToA2.emit(arciere);
  }
  addB1(arciere: Arciere){
    this.addToB1.emit(arciere);
  }
  addB2(arciere: Arciere){
    this.addToB2.emit(arciere);
  }

  /*getCanBeSelectedFunction(){
    return (item: Arciere) => {return this.gruppoA.find((e) => { return item.id == e.id;}) == undefined &&
      this.gruppoB.find((e) => {return item.id == e.id;}) == undefined};
  }*/

  filterChanged(filter){
    this.arcieriFiltered = this.arcieri.filter( arciere => {
      if(filter){
        return arciere.nome.toUpperCase().startsWith(filter.toUpperCase()) || arciere.cognome.toUpperCase().startsWith(filter.toUpperCase());
      }
      else{
        return true;
      }
    });
    this.arcieriSubject.next(this.arcieriFiltered);
  }

  isBoolean(val){
    return typeof val === 'boolean';
  }

  _getDisplayValue(item){
    if(this.isBoolean(item)){
      return this.translate.instant(""+item);
    }
    if(Array.isArray(item)){
      let string = "";
      for(let i of item){
        string += this._getDisplayValue(i) + " ";
      }
      return string;
    }
    if(TipoGara.isTipoGara(item)){
      return item.nome;
    }
    return item;
  }

  getDisplayValue(){
    return (item) => {return this._getDisplayValue(item)};
  }

  getLabel(gruppo){
    switch(gruppo){
      case "A1": return this.numGruppi > 1 ? "A1" : "A";
      case "A2": return this.numGruppi > 1 ? "A2" : "A";
      case "B1": return this.numGruppi > 1 ? "B1" : "B";
      case "B2": return this.numGruppi > 1 ? "B2" : "B";
    }
    return "ERROR";
  }

}
