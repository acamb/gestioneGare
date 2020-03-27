import {Arciere} from "./Arciere";
import {TipoGara} from "./TipoGara";
import {Divisione} from "./Divisione";
export class TemplateGara {
  id: number;
  descrizione: string;
  punteggi: number;
}
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
  templateGara: TemplateGara;

  constructor(){
    this.tipiGara=[];
    this.gruppoA1=[];
    this.gruppoB1=[];
    this.gruppoA2=[];
    this.gruppoB2=[];
    this.divisioni=[];
  }

}
