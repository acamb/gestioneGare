export class TipoGara {
  id: number;
  nome: string;
  selected: boolean;


  static isTipoGara(obj) : obj is TipoGara{
    return obj.id !== undefined && obj.nome !== undefined;
  }
}


