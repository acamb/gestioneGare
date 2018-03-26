package acambieri.sanbernardo.gestionegare.mapper;

import acambieri.sanbernardo.gestionegare.model.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface GareMapper {

    @Insert(
            "INSERT INTO GARE (NOME,ANNO_SOCIETARIO,PUNTEGGIO_MASSIMO) VALUES (#{gara.nome},#{gara.annoSocietario},#{gara.punteggioMassimo})"
    )
    @Options(useGeneratedKeys=true, keyProperty="gara.id")
    void saveGara(@Param("gara") Gara gara);

    @Update(
            "UPDATE GARE SET NOME=#{gara.nome}," +
                    "ANNO_SOCIETARIO=#{gara.annoSocietario}," +
                    "PUNTEGGIO_MASSIMO=#{gara.punteggioMassimo} " +
                    "WHERE ID = #{gara.id}"
    )
    void updateGara(@Param("gara") Gara gara);

    @Insert(
            "INSERT INTO DIVISIONI_GARA (ID_DIVISIONE,ID_GARA) VALUES (#{divisione},#{gara})"
    )
    void saveAssociazioneDivisione(@Param("divisione") long divisione, @Param("gara") long gara);

    @Insert(
            "INSERT INTO GARE_TIPI_GARA (ID_GARA,ID_TIPO) VALUES (#{idGara},#{idTipo})"
    )
    void associaTipoGara(@Param("idGara") long idGara,@Param("idTipo") long idTipo);

    @Delete(
            "DELETE FROM GARE_TIPI_GARA WHERE ID_GARA = #{idGara}"
    )
    void cancellaTipiPerGara(@Param("idGara") long idGara);

    @Delete(
            "DELETE FROM DIVISIONI_GARA WHERE ID_GARA = #{idGara}"
    )
    void cancellaDivisioniPerGara(@Param("idGara") long idGara);

    @ResultMap("garaMap")
    @Select(
            "SELECT * FROM GARE WHERE ID = #{idGara}"
    )
    Gara getGara(@Param("idGara") long idGara);

    @ResultMap("confGaraMap")
    @Select(
            "SELECT CONF_GARA.*,\n" +
                    "GARE.ID GARA_ID,GARE.NOME GARA_NOME,GARE.ANNO_SOCIETARIO GARA_ANNO_SOCIETARIO,\n" +
                    "ARCIERI.ID ARCIERE_ID,ARCIERI.NOME ARCIERE_NOME, ARCIERI.COGNOME ARCIERE_COGNOME\n" +
                    ",DIVISIONI.ID DIV_ID, DIVISIONI.DESCRIZIONE DIV_DESCRIZIONE\n" +
                    "FROM ARCIERI,GARE,CONF_GARA\n" +
                    "LEFT OUTER JOIN DIVISIONI \n" +
                    "ON DIVISIONE = divisioni.ID \n" +
                    "WHERE CONF_GARA.ID_GARA = #{idGara} AND GARE.ID=CONF_GARA.ID_GARA AND ID_ARCIERE = ARCIERI.ID\n" +
                    "ORDER BY DIV_DESCRIZIONE,PUNTEGGIO DESC"
    )
    List<ConfGara> getConfGara(@Param("idGara") long idGara);

    @Insert(
            "INSERT INTO CONF_GARA (ID_GARA, ID_ARCIERE,GRUPPO,DIVISIONE,PUNTEGGIO,ESCLUDI_CLASSIFICA) VALUES (#{idGara},#{idArciere},#{gruppo},#{divisione},#{punteggio},#{escludi})"
    )
    void associaArciereAGara(@Param("idGara") long idGara,@Param("idArciere") long idArciere,@Param("gruppo") String gruppo,@Param("divisione") Long divisione,@Param("punteggio") int punteggio,@Param("escludi") String escludiClassifica);

    @Delete(
            "DELETE FROM CONF_GARA WHERE ID_GARA = #{idGara}"
    )
    void cancellaConfGara(@Param("idGara") long idGara);

    @Update(
            "UPDATE CONF_GARA SET PUNTEGGIO = #{punti} WHERE ID_GARA=#{idGara} AND ID_ARCIERE=#{idArciere}"
    )
    void updatePunteggio(@Param("idGara")long idGara,@Param("idArciere")long idArciere,@Param("punti") int punti);

    @ResultMap("garaMap")
    @Select(
            "SELECT * FROM GARE WHERE ANNO_SOCIETARIO = nvl(#{anno},anno_societario)"
    )
    List<Gara> getGare(@Param("anno") Integer anno);

    @ResultMap("garaMap")
    @Select(
            "SELECT GARE.* FROM GARE,GARE_TIPI_GARA WHERE ANNO_SOCIETARIO = nvl(#{anno},anno_societario) AND COMPLETATA='S' " +
                    "AND ID_TIPO=#{tipo} AND GARE.ID = ID_GARA"
    )
    List<Gara> getGareCompletate(@Param("anno") int anno,@Param("tipo") long tipo);

    @Update(
           "UPDATE GARE SET COMPLETATA='S' WHERE ID=#{idGara}"
    )
    void setCompletata(@Param("idGara") long idGara);

    @Select(
            "SELECT * FROM TIPI_GARA"
    )
    List<TipoGara> getTipiGara();

    @ResultMap("arciereMap")
    @Select(
            "SELECT * FROM ARCIERI"
    )
    List<Arciere> getArcieri();

    @Select(
            "SELECT DATA_ANNO_SOCIETARIO FROM CONFIGURAZIONE"
    )
    String getDataAnnoSocietario();

    @ResultMap("divisioneMap")
    @Select(
            "SELECT * FROM DIVISIONI WHERE ENABLED='S' ORDER BY DESCRIZIONE"
    )
    List<Divisione> getDivisioni();

    @ResultMap("divisioneMap")
    @Select(
            "SELECT ID,DESCRIZIONE FROM DIVISIONI,DIVISIONI_GARA WHERE DIVISIONI.ID = DIVISIONI_GARA.ID_DIVISIONE AND ID_GARA = #{gara}"
    )
    List<Divisione> getDivisioniPerGara(@Param("gara")long idGara);

    @Insert(
            "INSERT INTO DIVISIONI (DESCRIZIONE,DEFAULT_GROUP,ENABLED) VALUES (#{div.descrizione},#{defaultGroup},'S')"
    )
    @Options(useGeneratedKeys=true, keyProperty="div.id")
    void saveDivisione(@Param("div") Divisione divisione, @Param("defaultGroup") String defaultGroup);

    @Update(
            "UPDATE DIVISIONI " +
                    "SET DESCRIZIONE=#{div.descrizione}, " +
                    "DEFAULT_GROUP=#{defaultGroup} " +
                    "WHERE ID = #{div.id}"
    )
    public void updateDivisione(@Param("div") Divisione divisione,@Param("defaultGroup") String defaultGroup);

    @Update(
            "UPDATE DIVISIONI " +
                    "SET ENABLED='N' " +
                    "WHERE ID = #{div.id}"
    )
    public void deleteDivisione(@Param("div") Divisione divisione);

}
