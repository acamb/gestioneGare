
import {Component, Input, OnChanges, SimpleChanges, EventEmitter, Output} from "@angular/core";
import {forEach} from "@angular/router/src/utils/collection";
import {TranslateService} from "@ngx-translate/core";
import {TipoGara} from "../../model/TipoGara";
@Component({
  selector: 'data-table',
  templateUrl: 'app.data-table.html',
  styleUrls: ['app.data-table.css']
}
)

export class DataTableComponent<T> implements OnChanges{
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
  @Input()
  selectable2:boolean=false;
  @Output()
  rowSelected = new EventEmitter<T>();
  @Output()
  rowSelected2 = new EventEmitter<T>();
  @Input()
  canBeSelected = (item) => {return true};
  @Input()
  showRowNumber = false;
  @Input()
  showHeaders=true;

 /* constructor () {
    this.keys= Object.keys(this.data[0]);
    if(this.headers.length === 0){
      this.headers = Object.keys(this.data[0]);
    }
  }*/

 constructor(private translate: TranslateService){

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

  isBoolean(val){
      return typeof val === 'boolean';
  }

  getDisplayValue(item){
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
  }

  selectRowHandler(item: T) {
    this.rowSelected.emit(item);
  }

  selectRowHandler2(item: T) {
    this.rowSelected2.emit(item);
  }

}
