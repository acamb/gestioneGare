package acambieri.sanbernardo.gestionegare.mappers;

import acambieri.sanbernardo.gestionegare.model.Arciere;
import acambieri.sanbernardo.gestionegare.model.ArciereVO;
import acambieri.sanbernardo.gestionegare.model.Gara;
import acambieri.sanbernardo.gestionegare.model.GaraVO;

public class ArciereVOMapper implements IMapper<Arciere, ArciereVO>{

    public ArciereVO toDto(Arciere source) {
        ArciereVO result = new ArciereVO();
        IMapper.super.toDto(source, result);
        return result;
    }

    public Arciere toEntity(ArciereVO source) {
        Arciere result = new Arciere();
        IMapper.super.toEntity(source, result);
        return result;
    }
}
