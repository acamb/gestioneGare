import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {TranslateService} from "@ngx-translate/core";
import {TipoGara} from "../../model/TipoGara";

@Component({
  selector: 'app-extensible-data-table',
  templateUrl: './extensible-data-table.component.html',
  styleUrls: ['./extensible-data-table.component.css']
})
export class ExtensibleDataTableComponent<T> implements OnInit,OnChanges {

  @Input()
  data: T[];
  keys: string[];
  @Input()
  headers: Object = {};
  @Input()
  selectKey = "seleziona";
  @Input()
  selectKey2 = "seleziona";
  @Input()
  itemsPerPage: number = 10;
  @Input()
  currentPage = 1;
  @Output()
  currentPageChange = new EventEmitter<number>();
  @Input()
  paginated:boolean = false;
  @Input()
  selectable:boolean=false;

  /*@Input()
  canBeSelected = (item) => {return true};*/
  @Input()
  getDisplayValue = (item) => {return item};
  @Input()
  showRowNumber = false;
  @Input()
  showHeaders=true;
  @Input()
  itemTemplate = "<div>{{item | json}}</div>"

  constructor(private translate: TranslateService){

  }

  ngOnInit() {
  }

  pageChange(event) {
    this.currentPage = event.page;
    this.currentPageChange.emit(event.page);
  }


  get pagedItems(){
    if (!this.paginated) {
      return this.data;
    }
    const first = (this.currentPage - 1) * this.itemsPerPage;
    return this.data.slice( first, first + this.itemsPerPage );
  }

  ngOnChanges(changes: SimpleChanges): void {

    if(Object.keys(this.headers).length === 0){
      this.keys= Object.keys(this.data[0]);
      this.keys.forEach(key => {
        this.headers[key] = key;
      })
    }
    else{
      this.keys=Object.keys(this.headers);
    }
  }



  /*getDisplayValue(item){
    if(this.isBoolean(item)){
      return this.translate.instant(""+item);
    }
    if(Array.isArray(item)){
      let string = "";
      for(let i of item){
        string += this.getDisplayValue(i) + " ";
      }
      return string;
    }
    if(TipoGara.isTipoGara(item)){
      return item.nome;
    }
    return item;
  }*/

}
