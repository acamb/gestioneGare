import { Component, OnInit } from '@angular/core';
import {Gara} from "../../model/Gara";
import {ActivatedRoute} from "@angular/router";
import {Arciere} from "../../model/Arciere";
import {Observable} from "rxjs/Observable";
import {GareService} from "../../gare.service";

@Component({
  selector: 'app-modifica-gara-container',
  templateUrl: './modifica-gara-container.component.html',
  styleUrls: ['./modifica-gara-container.component.css']
})
export class ModificaGaraContainerComponent implements OnInit {

  gara: Gara;
  arcieri: Observable<Array<Arciere>>;

  constructor(private route: ActivatedRoute,gareService: GareService) {
    this.gara = route.snapshot.data["garaDaEditare"];
    this.arcieri = gareService.getArcieri();
  }

  ngOnInit() {
  }

}
