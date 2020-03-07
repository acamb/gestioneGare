import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {TipoGara} from "../../model/TipoGara";
import {Arciere} from "../../model/Arciere";
import {GareService} from "../../gare.service";
import {GruppiContainer} from "../../model/GruppiContainer";
import {SelectableItem} from "../../model/SelectableItem";
import {Observable} from "rxjs/Observable";
import 'rxjs/add/operator/toPromise';
import {Router} from "@angular/router";
import {Gara, TemplateGara} from "../../model/Gara";
import {Divisione} from "../../model/Divisione";
import {init} from "protractor/built/launcher";
import {StickyOnScrollDirective} from "../../directives/sticky-on-scroll.directive";

@Component({
  selector: 'app-creazione-gara',
  templateUrl: './creazione-gara.component.html',
  styleUrls: ['./creazione-gara.component.css']
})
export class CreazioneGaraComponent implements OnInit {

  annoSocietario: number;
  tipiGara : Observable<Array<TipoGara>>;
  divisioni: Array<Divisione>;
  @Input()
  arcieri: Observable<Array<Arciere>>;
  data: Date;
  gruppoA : Array<Arciere>;
  gruppoB : Array<Arciere>;
  saving = false;
  @Input()
  edit = false;
  @Input()
  garaDaEditare: Gara=new Gara();
  checked=[];
  headersDivisioni= { descrizione: "descrizione"};
  numeroCoppie=1;
  templateGara: Observable<Array<TemplateGara>>;
  selectedTemplateGara: TemplateGara;

  @ViewChild('duplicaModal') modal;

  constructor(private gareService : GareService,private router: Router) {
    this.tipiGara = this.gareService.getTipiGara();
    this.templateGara = this.gareService.getTemplateGare();

  }

  async init(){
    this.divisioni = await this.gareService.getDivisioni().toPromise();
    if(!this.edit){
      this.impostaAnno();
      this.garaDaEditare.divisioni = this.divisioni.filter(divisione => divisione.defaultGroup);
    }
    else{
      for(let tipo of this.garaDaEditare.tipiGara){
        this.checked[tipo.id]=true;
      }
      if(this.gareService.isGaraScontri(this.garaDaEditare)){
        this.numeroCoppie=2;
      }
      else{
        this.numeroCoppie=1;
      }
    }
  }

  async duplicaGara(gara: Gara){
    let gruppi = await this.gareService.duplicaGruppiGara(gara).toPromise();
    gruppi.gruppoA1.forEach(arciere => arciere.punteggio=0);
    gruppi.gruppoB1.forEach(arciere => arciere.punteggio=0);
    gruppi.gruppoA2.forEach(arciere => arciere.punteggio=0);
    gruppi.gruppoB2.forEach(arciere => arciere.punteggio=0);
    this.onChangeGruppi(gruppi);
    this.modal.close();
  }

  openDuplicaModal(){
    this.modal.open();
  }

  annulla(){
    this.modal.close();
  }

  async impostaAnno(){
    let anno = await this.gareService.getAnnoSocietario().toPromise();
    this.garaDaEditare.annoSocietario=anno;
  }

  ngOnInit() {
    this.init();
    if ( this.garaDaEditare.id != undefined){
      this.selectedTemplateGara = this.garaDaEditare.templateGara;
    }
  }

  toggleTipo(tipo: TipoGara){
    if(this.garaDaEditare.tipiGara.find(value => value.id == tipo.id)){
      this.garaDaEditare.tipiGara = this.garaDaEditare.tipiGara.filter(tipoG => tipoG.id != tipo.id);
      this.checked[tipo.id]=false;
    }
    else{
      this.garaDaEditare.tipiGara.push(tipo);
      this.checked[tipo.id]=true;
    }
    //se sono di tipo scontri ci sono 4 sottogruppi!
    if(this.garaDaEditare.tipiGara.find(tipo => (tipo.id == 3 || tipo.id==4))){
        this.numeroCoppie=2;
    }
    else{
      this.numeroCoppie=1;
    }
  }

  onChangeGruppi(gruppi: GruppiContainer){
    this.garaDaEditare.gruppoA1 = gruppi.gruppoA1;
    this.garaDaEditare.gruppoB1 = gruppi.gruppoB1;
    this.garaDaEditare.gruppoA2 = gruppi.gruppoA2;
    this.garaDaEditare.gruppoB2 = gruppi.gruppoB2;
  }

  async salva() {
    this.saving = true;
    this.garaDaEditare.templateGara = this.selectedTemplateGara;
    if (!this.edit) {
      this.garaDaEditare = await this.gareService.salvaGara(this.garaDaEditare).toPromise();
    }
    else{
      this.garaDaEditare = await this.gareService.updateGara(this.garaDaEditare).toPromise();
    }
    this.router.navigateByUrl("/gestioneGare");
    this.saving = false;
  }

  eliminaDivisione(divisione) {
      this.garaDaEditare.divisioni = this.garaDaEditare.divisioni.filter(mydivisione => mydivisione.id != divisione.id);
  }

  isDivisioneSelectable(divisione: Divisione) {
    return this.garaDaEditare.divisioni.find(divInList => divInList.id == divisione.id) == undefined;
  }

  addDivisione(divisione: Divisione) {
    if(!this.isDivisioneSelectable(divisione)){
      return;
    }
    this.garaDaEditare.divisioni.push(divisione);
  }
  compareTemplate = (a: TemplateGara,b: TemplateGara) => {
    if (a === undefined || b === undefined){
      return false;
    }
    return a.id === b.id;
  }
}
