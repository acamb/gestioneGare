import { Component, OnInit } from '@angular/core';
import {Gara} from "../../model/Gara";
import {Router} from "@angular/router";
import {GareService} from "../../gare.service";
import {init} from "protractor/built/launcher";
import {TipoGara} from "../../model/TipoGara";

@Component({
  selector: 'app-torneo-indoor',
  templateUrl: './torneo-indoor.component.html',
  styleUrls: ['./torneo-indoor.component.css']
})
export class TorneoIndoorComponent implements OnInit {

  searched=false;
  anno:number;
  gare: Array<Gara>;
  headersGare = {annoSocietario: 'Anno',nome: 'Nome'};

  constructor(private router: Router,private gareService: GareService) { }

  ngOnInit() {
    this.init();
  }

  async init(){
    this.anno = await this.gareService.getAnnoSocietario().toPromise();
  }


  classifica(gara: Gara){
    this.router.navigate(["/classifica",gara.id]);
  }

  async cerca(){
    let tipo = this.gareService.getTipoIndoor();
    this.gare = await this.gareService.getGareCompletateLight(this.anno,tipo).toPromise();
    this.searched=true;
  }

  classificaPerDivisioni() {
      this.router.navigate(['/classificaIndoorDivisioni',this.anno]);
  }

  classificaPerGruppi() {
      this.router.navigate(['/classificaIndoorGruppi',this.anno]);
  }
}
