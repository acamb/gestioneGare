

import {Divisione} from "./Divisione";

export interface Arciere{
  nome: String;
  cognome: String;
  id: number;
  punteggio: number;
  sesso: string;
  divisione: Divisione;
  escludiClassifica: boolean;
}
