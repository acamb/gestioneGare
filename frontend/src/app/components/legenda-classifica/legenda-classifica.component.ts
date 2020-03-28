import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-legenda-classifica',
  templateUrl: './legenda-classifica.component.html',
  styleUrls: ['./legenda-classifica.component.css']
})
export class LegendaClassificaComponent implements OnInit {

  @Input()
  templatePunti: Array<string>;



  constructor() { }

  ngOnInit() {
  }

}
