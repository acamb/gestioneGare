import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Gara} from "../../model/Gara";
import {Scontro, Turno} from "../../model/Turno";
import {Arciere} from "../../model/Arciere";
import {areIterablesEqual} from "@angular/core/src/change_detection/change_detection_util";

@Component({
  selector: 'app-gironi',
  templateUrl: './gironi.component.html',
  styleUrls: ['./gironi.component.css']
})
export class GironiComponent implements OnInit {

  gara: Gara;
  gironeA1: Array<Turno>;
  gironeA2: Array<Turno>;
  gironeB1: Array<Turno>;
  gironeB2: Array<Turno>;

  constructor(private route: ActivatedRoute) {
    this.gara = this.route.snapshot.data['gara'];
    if(this.gara.gruppoA1 && this.gara.gruppoA1.length > 0){
      this.gironeA1 = this.applicaAlgoritmoDiBerger(this.gara.gruppoA1);
    }
    if(this.gara.gruppoA2 && this.gara.gruppoA2.length > 0){
      this.gironeA2 = this.applicaAlgoritmoDiBerger(this.gara.gruppoA2);
    }
    if(this.gara.gruppoB1 && this.gara.gruppoB1.length > 0){
      this.gironeB1 = this.applicaAlgoritmoDiBerger(this.gara.gruppoB1);
    }
    if(this.gara.gruppoB2 && this.gara.gruppoB2.length > 0){
      this.gironeB2 = this.applicaAlgoritmoDiBerger(this.gara.gruppoB2);
    }
  }

  applicaAlgoritmoDiBerger(arcieri: Array<Arciere>) : Array<Turno>{
    const bye = {nome: 'BYE',cognome: null,divisione: null,id: 0,punteggio: 0,sesso: null,escludiClassifica:false,punteggi: Array<number>()};
    const turni = new Array<Turno>();
    if(arcieri.length % 2 ){
       arcieri.push(bye);
     }
/*     else{
       //aggiungo comunque i bay per i turni di riposo, anche se sono gia' pari
       arcieri.push(bye);
       arcieri.push(bye);
     }*/
     for(let i =0;i<arcieri.length-1;i++){
       let turno = new Turno();
       for(let j =0;j<arcieri.length/2;j++){
         let scontro = new Scontro();
         scontro.arciereUno = arcieri[j];
         scontro.arciereDue = arcieri[arcieri.length-j-1];
         turno.scontri.push(scontro);
       }
       turni.push(turno);
       //ruoto l'ultimo in prima posizione
       arcieri.splice(1,0,arcieri.pop());
     }
     return turni;
  }

  ngOnInit() {
  }

}
