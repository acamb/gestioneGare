import { Injectable } from '@angular/core';
import {Arciere} from "./model/Arciere";
import {TipoGara} from "./model/TipoGara";
import {Gara} from "./model/Gara";
import {SelectableItem} from "./model/SelectableItem";
import {Observable} from "rxjs/Observable";
import {Http} from "@angular/http";
import {environment} from "../environments/environment";
import {Divisione} from "./model/Divisione";
import {ClassificaPerDivisione} from "./model/ClassifichePerDivisione";
import {GruppiContainer} from "./model/GruppiContainer";
import "rxjs/add/operator/switchMap";


@Injectable()
export class GareService {

  mapTipi = new Map<string,TipoGara>();

  constructor(private http: Http) {
    this.initTipi();
  }

  async initTipi(){
    let tipi: Array<TipoGara> = await this.getTipiGara().toPromise();
    for(let tipo of tipi){
        this.mapTipi.set(tipo.nome,tipo);
    }
  }

  static getServer(): string{
    //let getUrl = window.location;
    //let baseUrl = getUrl .protocol + "//" + getUrl.host + environment.context
    let baseUrl = environment.server + environment.context
    return baseUrl;
  }

  getArcieri(): Observable<Array<Arciere>> {
     return this.http.get(GareService.getServer() + "gare/getArcieri")
       .map(this.extractData);
  }

  salvaGara(gara: Gara) : Observable<Gara>{
     return this.http.post(GareService.getServer() + "gare/salva",gara)
       .map(this.extractData);
  }

  updateGara(gara: Gara) : Observable<Gara>{
    return this.http.post(GareService.getServer() + "gare/update",gara)
      .map(this.extractData);
  }

  associaListaArcieri(gara : Gara){
    return this.http.put(GareService.getServer() + "gare/associaListe",gara)
      .map(this.extractData);
  }

  /**
   *
   * @returns {Array<Gara>}
   *
   * Ritorna una lista di gare per cui c'e' gia' una classifica, senza tirare su i gruppi e la classifica
   */
  getGareCompletateLight(anno,tipo: TipoGara) : Observable<Array<Gara>> {
    return this.http.get(GareService.getServer() + "gare/getGareCompletate?anno="+anno+"&tipo="+tipo.id)
      .map(this.extractData);
  }

  getGara(gara: Gara) : Observable<Gara>{
    return this.http.get(GareService.getServer() + "gare/getGara?id="+gara.id)
      .map(this.extractData);
  }


  salvaClassifica(gara: Gara){
    return this.http.put(GareService.getServer() + "gare/salvaClassifica",gara)
      .map(this.extractData);
  }

  getGare(anno) : Observable<Array<Gara>> {
      return this.http.get(GareService.getServer() + "gare/getGare?anno="+anno)
        .map(this.extractData);
  }

  extractData(res){
    return res.json() || {};
  }

  getTipiGara() : Observable<Array<TipoGara>>{
    return this.http.get(GareService.getServer() + "gare/getTipi")
      .map(this.extractData);
  }

  getAnnoSocietario() : Observable<number>{
    return this.http.get(GareService.getServer() + "gare/getAnnoSocietario")
      .map(this.extractData);
  }

  getDivisioni(): Observable<Array<Divisione>> {
    return this.http.get(GareService.getServer() + "gare/getDivisioni")
      .map(this.extractData);
  }

  getClassifichePerDivisione(gara: Gara): Observable<Array<ClassificaPerDivisione>> {
    return this.http.post(GareService.getServer() + "gare/getClassifiche",gara)
      .map(this.extractData);
  }

  getClassificheScontri(gara: Gara): Observable<Array<ClassificaPerDivisione>> {
    return this.http.post(GareService.getServer() + "gare/getClassificheScontri",gara)
      .map(this.extractData);
  }

  private getClassificaIndoorPerDivisione(anno: number) : Observable<Array<ClassificaPerDivisione>> {
    return this.http.get(GareService.getServer() + "gare/getClassificaIndoorDivisioni?anno="+anno)
      .map(this.extractData);

  }


  private getClassificaIndoorPerGruppi(anno: number) {
    return this.http.get(GareService.getServer() + "gare/getClassificaIndoorGruppi?anno="+anno)
      .map(this.extractData);

  }

  private getClassificaFiocchiPerDivisione(anno: number) {
    return this.http.get(GareService.getServer() + "gare/getClassificaFiocchiDivisioni?anno="+anno)
      .map(this.extractData);
  }

  private getClassificaFiocchiPerGruppi(anno: number) {
    return this.http.get(GareService.getServer() + "gare/getClassificaFiocchiGruppi?anno="+anno)
      .map(this.extractData);
  }

  getClassificaPerDivisione(anno: number,tipo: TipoGara){
    switch (tipo.id){
      case this.getTipoIndoor().id: return this.getClassificaIndoorPerDivisione(anno);
      case this.getTipoFiocchi().id: return this.getClassificaFiocchiPerDivisione(anno);
    }
  }

  getClassificaPerGruppi(anno: number, tipo: TipoGara){
    switch(tipo.id){
      case this.getTipoIndoor().id: return this.getClassificaIndoorPerGruppi(anno);
      case this.getTipoFiocchi().id: return this.getClassificaFiocchiPerGruppi(anno);
    }
  }


  getTipoIndoor() {
    return this.mapTipi.get("INDOOR");
  }

  getTipoFiocchi() {
    return this.mapTipi.get("FIOCCHI");
  }

  insertDivisione(divisione: Divisione) : Observable<Divisione>{
    return this.http.post(GareService.getServer() + "gare/divisioni/add",divisione)
      .map(this.extractData);
  }

  updateDivisione(divisione: Divisione){
    return this.http.post(GareService.getServer() + "gare/divisioni/update",divisione)
      .map(this.extractData);
  }

  deleteDivisione(divisione: Divisione){
    return this.http.post(GareService.getServer() + "gare/divisioni/delete",divisione)
      .map(this.extractData);
  }

  duplicaGruppiGara(gara: Gara) : Observable<GruppiContainer> {
    return this.getGara(gara)
      .map( g => {return {gruppoA1 : g.gruppoA1, gruppoB1: g.gruppoB1,gruppoA2 : g.gruppoA2, gruppoB2: g.gruppoB2} });
  }

  isGaraScontri(gara){
    return gara.tipiGara.find(t => t.id == 3 || t.id == 4);
  }

  backupDb(){
    return this.http.post(GareService.getServer() + "gare/backup" ,"")
      .map(this.extractData);
  }


}
