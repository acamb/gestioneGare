import { Component, OnInit } from '@angular/core';
import {GareService} from "../../gare.service";
import {ActivatedRoute} from "@angular/router";
import {ClassificaPerDivisione} from "../../model/ClassifichePerDivisione";
import {TipoGara} from "../../model/TipoGara";

@Component({
  selector: 'app-classifica-per-gruppi',
  templateUrl: './classifica-per-gruppi.component.html',
  styleUrls: ['./classifica-per-gruppi.component.css']
})
export class ClassificaPerGruppiComponent implements OnInit {

  anno: number;
  tipo: TipoGara;
  classifica: Array<ClassificaPerDivisione>;

  constructor(private route: ActivatedRoute,private gareService: GareService) {
    this.anno = route.snapshot.params['anno'];
    this.tipo = route.snapshot.data['tipo'];
    this.init();
  }

  async init(){
    this.classifica = await this.gareService.getClassificaPerGruppi(this.anno,this.tipo).toPromise();
  }

  ngOnInit() {
  }


}
