import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Arciere} from "../../model/Arciere";
import {Observable} from "rxjs/Observable";
import {GareService} from "../../gare.service";

@Component({
  selector: 'app-creazione-gara-container',
  templateUrl: './creazione-gara-container.component.html',
  styleUrls: ['./creazione-gara-container.component.css']
})
export class CreazioneGaraContainerComponent implements OnInit {

  arcieri : Observable<Array<Arciere>>

  constructor(route: ActivatedRoute,gareService: GareService) {
    this.arcieri = gareService.getArcieri();
  }

  ngOnInit() {
  }

}
