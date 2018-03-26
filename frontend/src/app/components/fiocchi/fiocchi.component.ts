import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {Gara} from "../../model/Gara";
import {GareService} from "../../gare.service";

@Component({
  selector: 'app-fiocchi',
  templateUrl: './fiocchi.component.html',
  styleUrls: ['./fiocchi.component.css']
})
export class FiocchiComponent implements OnInit {

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
    let tipo = this.gareService.getTipoFiocchi();
    this.gare = await this.gareService.getGareCompletateLight(this.anno,tipo).toPromise();
    this.searched=true;
  }

  classificaPerDivisioni() {
    this.router.navigate(['/classificaFiocchiDivisioni',this.anno]);
  }

  classificaPerGruppi() {
    this.router.navigate(['/classificaFiocchiGruppi',this.anno]);
  }

}
