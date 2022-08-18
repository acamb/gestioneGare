package acambieri.sanbernardo.gestionegare.mappers;

import acambieri.sanbernardo.gestionegare.model.Gara;
import acambieri.sanbernardo.gestionegare.model.GaraVO;

public class GaraVOMapper implements IMapper<Gara, GaraVO>{

    public GaraVO toDto(Gara source) {
        GaraVO result = new GaraVO();
        IMapper.super.toDto(source, result);
        return result;
    }

    public Gara toEntity(GaraVO source) {
        Gara result = new Gara();
        IMapper.super.toEntity(source, result);
        return result;
    }
}
