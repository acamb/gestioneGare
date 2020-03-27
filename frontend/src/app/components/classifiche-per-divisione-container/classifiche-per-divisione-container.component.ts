import {Component, Input, OnInit} from '@angular/core';
import {GareService} from "../../gare.service";
import {Arciere} from "../../model/Arciere";
import {Divisione} from "../../model/Divisione";
import {Gara} from "../../model/Gara";
import {ClassificaPerDivisione} from "../../model/ClassifichePerDivisione";

@Component({
  selector: 'app-classifiche-per-divisione-container',
  templateUrl: './classifiche-per-divisione-container.component.html',
  styleUrls: ['./classifiche-per-divisione-container.component.css']
})
export class ClassifichePerDivisioneContainerComponent implements OnInit {

  @Input()
  classifiche: Array<ClassificaPerDivisione>;
  @Input()
  pageBreakOnPrint=false;
  @Input()
  templatePunti: Array<string> = [ "" ];

  headersClassifica= {nome: "Nome",cognome: "Cognome",punteggio: "Punti"};

  constructor(private gareService: GareService) {

  }

  ngOnInit() {

  }

}
