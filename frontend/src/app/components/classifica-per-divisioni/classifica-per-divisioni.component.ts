import { Component, OnInit } from '@angular/core';
import {ClassificaPerDivisione} from "../../model/ClassifichePerDivisione";
import {TipoGara} from "../../model/TipoGara";
import {ActivatedRoute} from "@angular/router";
import {GareService} from "../../gare.service";

@Component({
  selector: 'app-classifica-per-divisioni',
  templateUrl: './classifica-per-divisioni.component.html',
  styleUrls: ['./classifica-per-divisioni.component.css']
})
export class ClassificaPerDivisioniComponent implements OnInit {

  anno: number;
  tipo: TipoGara;
  classifica: Array<ClassificaPerDivisione>;

  constructor(private route: ActivatedRoute,private gareService: GareService) {
    this.anno = route.snapshot.params['anno'];
    this.tipo = route.snapshot.data['tipo'];
    this.init();
  }

  async init(){
    this.classifica = await this.gareService.getClassificaPerDivisione(this.anno,this.tipo).toPromise();
  }

  ngOnInit() {
  }


}
