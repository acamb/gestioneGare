import {
  Component, ElementRef, EventEmitter, Input, OnChanges, OnInit, Output, QueryList, SimpleChanges,
  ViewChild
} from '@angular/core';
import {SortableComponent} from "ngx-bootstrap";
import {GruppiContainer} from "../../model/GruppiContainer";
import {Arciere} from "../../model/Arciere";
import {Divisione} from "../../model/Divisione";
import {Observable} from "rxjs/Observable";
import {Observer} from "rxjs/Observer";
import {Subject} from "rxjs/Subject";
import {Subscription} from "rxjs/Subscription";

@Component({
  selector: 'app-gruppi',
  templateUrl: './gruppi.component.html',
  styleUrls: ['./gruppi.component.css']
})
export class GruppiComponent implements OnInit {


  @Output()
  valueChangedEmitter = new EventEmitter<GruppiContainer>();

  @Input()
  gruppoUno = [];
  @Input()
  gruppoDue = [];

  @Input()
  divisioni : Array<Divisione>;
  @Input()
  gruppoUnoLabel = "A";
  @Input()
  gruppoDueLabel = "B";


  ngOnInit(): void {
    console.log("breakpoint");
  }

  @ViewChild('gruppoUnoComponent')
  sortableComponentUno : SortableComponent;

  @ViewChild('gruppoDueComponent')
  sortableComponentDue : SortableComponent;



  constructor(private el: ElementRef) {
    //this.arcieriSubject.next([]);
  }


  addUno(arciere){
    console.log("addUno di group");
    arciere.currentGroup=this.gruppoUnoLabel;
    this.gruppoUno.push(arciere);
    this.sortableComponentUno.writeValue(this.gruppoUno);
    this.recalcHeigth();
  }

  addDue(arciere){
    arciere.currentGroup=this.gruppoDueLabel;
    this.gruppoDue.push(arciere);
    this.sortableComponentDue.writeValue(this.gruppoDue);
    this.recalcHeigth();
  }

  eliminaA(item){
    this.gruppoUno=this.gruppoUno.filter(arciere => arciere.id != item.value.id);
    item.value.currentGroup=undefined;
    this.sortableComponentUno.writeValue(this.gruppoUno);
  }

  eliminaB(item){
    this.gruppoDue=this.gruppoDue.filter(arciere => arciere.id != item.value.id);
    item.value.currentGroup=undefined;
    this.sortableComponentDue.writeValue(this.gruppoDue);
  }

  onChangeGruppo(){
    this.recalcHeigth();
    this.valueChangedEmitter.emit({gruppoA1: this.gruppoUno,gruppoB1: this.gruppoDue,gruppoA2:undefined,gruppoB2:undefined});
  }

  recalcHeigth(){
    setTimeout(()=> {
      let gruppi = this.el.nativeElement.getElementsByClassName('gruppoContainer');
      let maxHeight = 0;
      for (let child of gruppi) {
        child.style.height = 'initial';
      }
      for (let child of gruppi) {
        if (child.getBoundingClientRect().height > maxHeight) {
          maxHeight = child.getBoundingClientRect().height;
        }
      }
      for (let child of gruppi) {
        child.style.height = '' + maxHeight + 'px';
      }
    },50);
  }

  cambiaDivisione(arciere, divisione: Divisione){
      arciere.divisione = divisione;
      this.valueChangedEmitter.emit({gruppoA1: this.gruppoUno,gruppoB1: this.gruppoDue,gruppoA2: undefined,gruppoB2: undefined});
  }

  toogleGruppoG(arciere: Arciere){
    arciere.escludiClassifica= !arciere.escludiClassifica;
  }

}
