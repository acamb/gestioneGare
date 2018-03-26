package acambieri.sanbernardo.gestionegare.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component

public class CommonDao implements DisposableBean{

    protected SqlSession session;


    public CommonDao(SqlSessionFactory factory){
        this.session= factory.openSession();
    }

    @Override
    public void destroy() throws Exception {
        commit();
        closeSession();
    }

    public void commit(){
        session.commit();
    }

    public void rollback(){
        session.rollback();
        session.close();
    }

    public void closeSession(){
        session.close();
    }
}
