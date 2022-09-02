import { Component, OnInit } from '@angular/core';
import {GareService} from "../../gare.service";
import {Observable} from "rxjs";
import {Gara} from "../../model/Gara";
import {Router} from "@angular/router";
import {TipoGara} from "../../model/TipoGara";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-gestione-gare',
  templateUrl: './gestione-gare.component.html',
  styleUrls: ['./gestione-gare.component.css']
})
export class GestioneGareComponent implements OnInit {

  gare: Observable<Array<Gara>>;
  headersGare = {annoSocietario: 'Anno',nome: 'Nome',tipiGara: 'Tipi gara'};

  constructor(private gareService: GareService,private router : Router,private translate: TranslateService) {
    this.init();
  }

  ngOnInit() {
  }

  init(){
    //let anno = await this.gareService.getAnnoSocietario().toPromise();
    this.gare = this.gareService.getGare(0);
  }


  modifica(gara: Gara){
    this.router.navigate(["/modifica",gara.id]);
  }

  classifica(gara: Gara){
    this.router.navigate(["/classifica",gara.id]);
  }

  gironi(gara: Gara){
    this.router.navigate(["/gironi",gara.id]);
  }

  isGaraScontri(gara: Gara){
    return this.gareService.isGaraScontri(gara);
  }

  isBoolean(val){
    return typeof val === 'boolean';
  }

  getDisplayValue(item){
    if(this.isBoolean(item)){
      return this.translate.instant(""+item);
    }
    if(Array.isArray(item)){
      let string = "";
      for(let i of item){
        string += this.getDisplayValue(i) + " ";
      }
      return string;
    }
    if(TipoGara.isTipoGara(item)){
      return item.nome;
    }
    return item;
  }
  getDisplayValueFunction(){
    return (item) => this.getDisplayValue(item);
  }
}
