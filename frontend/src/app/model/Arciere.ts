

import {Divisione} from "./Divisione";

export interface TemplatePunteggio {
  id:number;
  descrizione:string;
  ordine:number;
}

export interface Punteggio {
  id:number;
  punteggio:number;
  templatePunteggio: TemplatePunteggio;
}

export interface Arciere{
  nome: String;
  cognome: String;
  id: number;
  punteggio: number;
  punteggi: Array<Punteggio>;
  sesso: string;
  divisione: Divisione;
  escludiClassifica: boolean;

}
