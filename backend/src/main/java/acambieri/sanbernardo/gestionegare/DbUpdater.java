/*
package acambieri.sanbernardo.gestionegare;

import acambieri.sanbernardo.gestionegare.dao.UpdateDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class DbUpdater implements ServletContextInitializer {

    @Value("classpath:/updates/*")
    private Resource[] dbUpdates;

    @Autowired
    private MySqlSessionFactory factory;


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        SqlSessionFactory session = factory.getSession();
        final UpdateDao updateDao = new UpdateDao(session);
        List<Resource> updateList = Arrays.asList(dbUpdates);
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        AtomicInteger lastUpdate = new AtomicInteger(0);
        updateList.stream().filter(resource -> {
            return Integer.parseInt(resource.getFilename()) > updateDao.getDbVersion();
        }).sorted(new Comparator<Resource>() {
            @Override
            public int compare(Resource o1, Resource o2) {
                return Integer.parseInt(o1.getFilename()) - Integer.parseInt(o2.getFilename());
            }
        }).forEach(resource -> {
            populator.addScript(resource);
            lastUpdate.set(Integer.parseInt(resource.getFilename()));
        });
        DatabasePopulatorUtils.execute(populator,session.getConfiguration().getEnvironment().getDataSource());
        if(lastUpdate.get() != 0) {
            updateDao.incrementDbVersion(lastUpdate.get());
        }
        updateDao.commit();
        updateDao.closeSession();
    }
}
*/
