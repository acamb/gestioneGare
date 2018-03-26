import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Gara} from "../../model/Gara";
import {GareService} from "../../gare.service";
import {Arciere} from "../../model/Arciere";
import {Divisione} from "../../model/Divisione";
import {ClassificaPerDivisione} from "../../model/ClassifichePerDivisione";

@Component({
  selector: 'app-classifica-gara',
  templateUrl: './classifica-gara.component.html',
  styleUrls: ['./classifica-gara.component.css']
})
export class ClassificaGaraComponent implements OnInit {

  gara: Gara;
  modifica=false;
  saving=false;
  classifichePerDivisione: Array<ClassificaPerDivisione>;
  classifichePerGruppi: Array<ClassificaPerDivisione>;


  constructor(private route: ActivatedRoute,private router : Router,private gareService: GareService) {
    this.gara = this.route.snapshot.data['gara'];
    this.getClassifiche();
  }

  ngOnInit() {

  }

  async getClassifiche(){
    if(this.isGaraScontri(this.gara)){
      this.classifichePerGruppi = await this.gareService.getClassificheScontri(this.gara).toPromise();
    }
    else{
      this.classifichePerDivisione = await this.gareService.getClassifichePerDivisione(this.gara).toPromise();
    }
  }

  toggleModifica(){
    this.modifica=true;
  }

  async salva(){
    this.saving=true;
    await this.gareService.salvaClassifica(this.gara).toPromise();
    this.gara = await this.gareService.getGara(this.gara).toPromise();
    this.modifica=false;
    await this.getClassifiche();
    this.saving=false;
  }


  isGaraScontri(gara){
    return this.gareService.isGaraScontri(gara);
  }

}
