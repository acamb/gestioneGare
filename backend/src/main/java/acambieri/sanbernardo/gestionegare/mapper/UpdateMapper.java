package acambieri.sanbernardo.gestionegare.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UpdateMapper {

    @Update(
            "${sql}"
    )
    public void executeSql(@Param("sql") String sql);

    @Update(
            "UPDATE configurazione set DB_VERSION = #{version}"
    )
    public void incrementaVersioneDb(@Param("version") int version);

    @Select(
            "SELECT DB_VERSION FROM CONFIGURAZIONE"
    )
    public int getDbVersion();

    @Update(
            "SCRIPT TO 'backup.sql'"
    )
    public void backupDb();
}
