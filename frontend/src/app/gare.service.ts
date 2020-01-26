import { Injectable } from '@angular/core';
import {Arciere} from "./model/Arciere";
import {TipoGara} from "./model/TipoGara";
import {Gara} from "./model/Gara";
import {SelectableItem} from "./model/SelectableItem";
import {Observable} from "rxjs/Observable";
import {HttpClient} from "@angular/common/http";
import {environment} from "../environments/environment";
import {Divisione} from "./model/Divisione";
import {ClassificaPerDivisione} from "./model/ClassifichePerDivisione";
import {GruppiContainer} from "./model/GruppiContainer";
import "rxjs/add/operator/switchMap";
import {extractData} from "./app.module";


@Injectable()
export class GareService {

  mapTipi = new Map<string,TipoGara>();

  constructor(private http: HttpClient) {
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

  }

  salvaGara(gara: Gara) : Observable<Gara>{
     return this.http.post(GareService.getServer() + "gare/salva",gara)

  }

  updateGara(gara: Gara) : Observable<Gara>{
    return this.http.post(GareService.getServer() + "gare/update",gara)

  }

  associaListaArcieri(gara : Gara): any{
    return this.http.put(GareService.getServer() + "gare/associaListe",gara)

  }

  /**
   *
   * @returns {Array<Gara>}
   *
   * Ritorna una lista di gare per cui c'e' gia' una classifica, senza tirare su i gruppi e la classifica
   */
  getGareCompletateLight(anno,tipo: TipoGara) : Observable<Array<Gara>> {
    return this.http.get(GareService.getServer() + "gare/getGareCompletate?anno="+anno+"&tipo="+tipo.id)

  }

  getGara(gara: Gara) : Observable<Gara>{
    return this.http.get(GareService.getServer() + "gare/getGara?id="+gara.id)

  }


  salvaClassifica(gara: Gara): any{
    return this.http.put(GareService.getServer() + "gare/salvaClassifica",gara)

  }

  getGare(anno) : Observable<Array<Gara>> {
      return this.http.get(GareService.getServer() + "gare/getGare?anno="+anno)

  }


  getTipiGara() : Observable<Array<TipoGara>>{
    return this.http.get(GareService.getServer() + "gare/getTipi")

  }

  getAnnoSocietario() : Observable<number>{
    return this.http.get(GareService.getServer() + "gare/getAnnoSocietario")

  }

  getDivisioni(): Observable<Array<Divisione>> {
    return this.http.get(GareService.getServer() + "gare/getDivisioni")

  }

  getClassifichePerDivisione(gara: Gara): Observable<Array<ClassificaPerDivisione>> {
    return this.http.post(GareService.getServer() + "gare/getClassifiche",gara)

  }

  getClassificheScontri(gara: Gara): Observable<Array<ClassificaPerDivisione>> {
    return this.http.post(GareService.getServer() + "gare/getClassificheScontri",gara)

  }

  private getClassificaIndoorPerDivisione(anno: number) : Observable<Array<ClassificaPerDivisione>> {
    return this.http.get(GareService.getServer() + "gare/getClassificaIndoorDivisioni?anno="+anno)


  }


  private getClassificaIndoorPerGruppi(anno: number) : any {
    return this.http.get(GareService.getServer() + "gare/getClassificaIndoorGruppi?anno="+anno)


  }

  private getClassificaFiocchiPerDivisione(anno: number) : any{
    return this.http.get(GareService.getServer() + "gare/getClassificaFiocchiDivisioni?anno="+anno)

  }

  private getClassificaFiocchiPerGruppi(anno: number): any {
    return this.http.get(GareService.getServer() + "gare/getClassificaFiocchiGruppi?anno="+anno)

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

  }

  updateDivisione(divisione: Divisione){
    return this.http.post(GareService.getServer() + "gare/divisioni/update",divisione)

  }

  deleteDivisione(divisione: Divisione){
    return this.http.post(GareService.getServer() + "gare/divisioni/delete",divisione)

  }

  duplicaGruppiGara(gara: Gara) : Observable<GruppiContainer> {
    return this.getGara(gara)
      .map( g => {return {gruppoA1 : g.gruppoA1, gruppoB1: g.gruppoB1,gruppoA2 : g.gruppoA2, gruppoB2: g.gruppoB2} });
  }

  isGaraScontri(gara){
    return gara.tipiGara.find(t => t.id == 3 || t.id == 4);
  }

  backupDb() : any{
    return this.http.post(GareService.getServer() + "gare/backup" ,"")

  }


}
