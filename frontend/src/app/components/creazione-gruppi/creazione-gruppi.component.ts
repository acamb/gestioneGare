import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {Divisione} from "../../model/Divisione";
import {Arciere} from "../../model/Arciere";
import {GruppiContainer} from "../../model/GruppiContainer";
import {Observable, Subject} from "rxjs";
import {GruppiComponent} from "../gruppi/gruppi.component";
import {Gara} from "../../model/Gara";

@Component({
  selector: 'app-creazione-gruppi',
  templateUrl: './creazione-gruppi.component.html',
  styleUrls: ['./creazione-gruppi.component.css']
})
export class CreazioneGruppiComponent implements OnInit {


  @Input()
  numeroCoppie=1
  @Input()
  divisioni: Array<Divisione>;
  @Input()
  arcieri: Observable<Array<Arciere>>;
  @Output()
  onChangeGruppi = new EventEmitter<GruppiContainer>();
  @Input()
  gara : Gara;

  @ViewChild('gruppo', { static: false })
  gruppoSingolo : GruppiComponent;

  @ViewChild('gruppo1', { static: false })
  gruppoUno : GruppiComponent;

  @ViewChild('gruppo2', { static: false })
  gruppoDue : GruppiComponent;

  constructor() { }

  ngOnInit() {

  }

  gruppiChanged(gruppi: GruppiContainer){
    this.onChangeGruppi.emit(gruppi);
    this.gara.gruppoA1 = gruppi.gruppoA1;
    this.gara.gruppoB1 = gruppi.gruppoB1;
  }

  gruppiAChanged(gruppi: GruppiContainer){
    //il componente gruppi.container passa sempre i gruppi in A1 e B1
    this.gara.gruppoA1 = gruppi.gruppoA1;
    this.gara.gruppoA2 = gruppi.gruppoB1;
    this.onChangeGruppi.emit({gruppoA1: gruppi.gruppoA1,gruppoA2: gruppi.gruppoB1,gruppoB1: this.gara.gruppoB1,gruppoB2: this.gara.gruppoB2});
  }

  gruppiBChanged(gruppi: GruppiContainer){
    //il componente gruppi.container passa sempre i gruppi in A1 e B1
    this.gara.gruppoB1 = gruppi.gruppoA1;
    this.gara.gruppoB2 = gruppi.gruppoB1;
    this.onChangeGruppi.emit({gruppoA1: this.gara.gruppoA1,gruppoA2: this.gara.gruppoA2,gruppoB1: gruppi.gruppoA1,gruppoB2: gruppi.gruppoB1});
  }

  addToA1(arciere: Arciere){
    console.log("addToA1 di creazione-gruppi");
    //this.gara.gruppoA1.push(arciere);
    if(this.numeroCoppie == 1){
      this.gruppoSingolo.addUno(arciere);
    }
    else{
      this.gruppoUno.addUno(arciere);
    }
  }
  addToA2(arciere: Arciere){
    //this.gara.gruppoA2.push(arciere);
    this.gruppoUno.addDue(arciere);
  }
  addToB1(arciere: Arciere){
    //this.gara.gruppoB1.push(arciere);
    if(this.numeroCoppie == 1){
      this.gruppoSingolo.addDue(arciere);
    }
    else{
      this.gruppoDue.addUno(arciere);
    }
  }
  addToB2(arciere: Arciere){
    //this.gara.gruppoB2.push(arciere);
    this.gruppoDue.addDue(arciere);
  }


  getCanBeSelectedFunction(){
    return (item: Arciere) => {
      return !this.gara.gruppoA1.concat(this.gara.gruppoA2).concat(this.gara.gruppoB1).concat(this.gara.gruppoB2).find( arciere => item.id === arciere.id);
    }
  }

}
