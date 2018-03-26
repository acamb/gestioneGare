package acambieri.sanbernardo.gestionegare;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;

/**
 * @author andrea AC
 *         Date: 22/11/2016
 */

@Configuration
public class MySqlSessionFactory {

    private Environment environment;

    private SqlSessionFactory factory;

    public MySqlSessionFactory(@Autowired Environment environment,
                               @Value("classpath:/schema.sql") Resource schema,
                               @Value("classpath:/data.sql") Resource data){
        this.environment=environment;
        Reader reader = null;
        boolean testProfile = false;
        testProfile=Arrays.asList(environment.getActiveProfiles()).contains("test");
        try {
            if(testProfile) {
                reader = new InputStreamReader(new ClassPathResource("myBatis_test.xml").getInputStream());
            }
            else{
                reader = new InputStreamReader(new ClassPathResource("myBatis.xml").getInputStream());
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("File di configurazione mybatis non trovato: " + Utils.exceptionToString(e));
        }
        factory = new SqlSessionFactoryBuilder().build( reader );
        if(testProfile){
            ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
            if(schema != null && schema.exists()) {
                populator.addScript(schema);
            }
            if(data != null && data.exists()) {
                populator.addScript(data);
            }
            DatabasePopulatorUtils.execute(populator,factory.getConfiguration().getEnvironment().getDataSource());
        }
    }



    @Bean
    @Qualifier("myFactory")
    public SqlSessionFactory getSession(){
        return factory;
    }
}
