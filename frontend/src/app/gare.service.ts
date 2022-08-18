import { Injectable } from '@angular/core';
import {Arciere} from "./model/Arciere";
import {TipoGara} from "./model/TipoGara";
import {Gara, TemplateGara} from "./model/Gara";
import {Observable} from "rxjs/Observable";
import {HttpClient} from "@angular/common/http";

import {Divisione} from "./model/Divisione";
import {ClassificaPerDivisione} from "./model/ClassifichePerDivisione";
import {GruppiContainer} from "./model/GruppiContainer";
import "rxjs/add/operator/switchMap";
import { getServer } from './Utils';
import { RequestOptions } from '@angular/http';


@Injectable()
export class GareService {

  mapTipi = new Map<string,TipoGara>();

  constructor(private http: HttpClient) {

  }

  initTipi(){
    this.getTipiGara().map(
      tipi => tipi.forEach(tipo => this.mapTipi.set(tipo.nome,tipo))
    ).take(1).subscribe();
  }



  getArcieri(): Observable<Array<Arciere>> {
     return this.http.get<Array<Arciere>>(getServer() + "gare/getArcieri")

  }

  salvaGara(gara: Gara) : Observable<Gara>{
     return this.http.post(getServer() + "gare/salva",gara)

  }

  updateGara(gara: Gara) : Observable<Gara>{
    return this.http.post(getServer() + "gare/update",gara)

  }

  getGareCompletateLight(anno,tipo: TipoGara) : Observable<Array<Gara>> {
    return this.http.get(getServer() + "gare/getGareCompletate?anno="+anno+"&tipo="+tipo.id)

  }

  getGara(gara: Gara) : Observable<Gara>{
    return this.http.get(getServer() + "gare/getGara?id="+gara.id)

  }


  salvaClassifica(gara: Gara): any{
    return this.http.put(getServer() + "gare/salvaClassifica",gara)

  }

  getGare(anno) : Observable<Array<Gara>> {
      return this.http.get(getServer() + "gare/getGare?anno="+anno)

  }


  getTipiGara() : Observable<Array<TipoGara>>{
    return this.http.get<Array<TipoGara>>(getServer() + "gare/getTipi")

  }

  getAnnoSocietario() : Observable<number>{
    return this.http.get(getServer() + "gare/getAnnoSocietario")

  }

  getDivisioni(): Observable<Array<Divisione>> {
    return this.http.get(getServer() + "divisione/")

  }

  getClassifichePerDivisione(gara: Gara): Observable<Array<ClassificaPerDivisione>> {
    return this.http.post(getServer() + "gare/getClassifiche",gara)

  }

  getClassificheScontri(gara: Gara): Observable<Array<ClassificaPerDivisione>> {
    return this.http.post(getServer() + "gare/getClassificheScontri",gara)

  }

  private getClassificaIndoorPerDivisione(anno: number) : Observable<Array<ClassificaPerDivisione>> {
    return this.http.get(getServer() + "gare/getClassificaIndoorDivisioni?anno="+anno)


  }


  private getClassificaIndoorPerGruppi(anno: number) : any {
    return this.http.get(getServer() + "gare/getClassificaIndoorGruppi?anno="+anno)


  }

  private getClassificaFiocchiPerDivisione(anno: number) : any{
    return this.http.get(getServer() + "gare/getClassificaFiocchiDivisioni?anno="+anno)

  }

  private getClassificaFiocchiPerGruppi(anno: number): any {
    return this.http.get(getServer() + "gare/getClassificaFiocchiGruppi?anno="+anno)

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
    return this.http.post<Divisione>(getServer() + "divisione/",divisione)

  }

  updateDivisione(divisione: Divisione){
    return this.http.put(getServer() + "divisione/",divisione)

  }

  deleteDivisione(divisione: Divisione){
    return this.http.delete(getServer() + "divisione/"+divisione.id)

  }

  duplicaGruppiGara(gara: Gara) : Observable<GruppiContainer> {
    return this.getGara(gara)
      .map( g => {return {gruppoA1 : g.gruppoA1, gruppoB1: g.gruppoB1,gruppoA2 : g.gruppoA2, gruppoB2: g.gruppoB2} });
  }

  isGaraScontri(gara){
    return gara.tipiGara.find(t => t.id == 3 || t.id == 4);
  }

  backupDb() : any{
    return this.http.post(getServer() + "gare/backup" ,"")

  }

  getTemplateGare(){
    return this.http.post<Array<TemplateGara>>(getServer() + "gare/getTemplateGare","")
  }

  getTemplatePunti( gara: Gara ) : Observable<Array<string>> {
    return this.http.get<Array<string>>(getServer() + "gare/getTemplatePunti?id="+gara.id);
  }


}
