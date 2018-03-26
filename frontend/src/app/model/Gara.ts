import {Arciere} from "./Arciere";
import {TipoGara} from "./TipoGara";
import {Divisione} from "./Divisione";

export class Gara{
  gruppoA1: Array<Arciere>;
  gruppoB1: Array<Arciere>;
  gruppoA2: Array<Arciere>;
  gruppoB2: Array<Arciere>;
  annoSocietario: number;
  tipiGara: Array<TipoGara>;
  divisioni: Array<Divisione>
  punteggioMassimo: number;
  nome: string;
  id: number;

  constructor(){
    this.tipiGara=[];
    this.gruppoA1=[];
    this.gruppoB1=[];
    this.gruppoA2=[];
    this.gruppoB2=[];
    this.divisioni=[];
  }

}
