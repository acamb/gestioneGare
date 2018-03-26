package acambieri.sanbernardo.gestionegare.dao;

import acambieri.sanbernardo.gestionegare.mapper.UpdateMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@Scope(value="request", proxyMode= ScopedProxyMode.TARGET_CLASS)
public class UpdateDao extends CommonDao {

    private UpdateMapper mapper;

    @Autowired
    public UpdateDao(@Qualifier("myFactory")SqlSessionFactory factory){
        super(factory);
        mapper = session.getMapper(UpdateMapper.class);
    }


    public int getDbVersion(){
        return mapper.getDbVersion();
    }

    public void executeUpdate(String sql){
        mapper.executeSql(sql);
    }

    public void incrementDbVersion(int version){
        mapper.incrementaVersioneDb(version);
    }

    public void incrementDbVersion(String version){
        incrementDbVersion(Integer.parseInt(version));
    }

    public File backupDb(){
        File f = new File("backup.sql");
        mapper.backupDb();
        return f;
    }
}
