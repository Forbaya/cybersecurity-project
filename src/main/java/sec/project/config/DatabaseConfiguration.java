package sec.project.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import sec.project.controller.DatabaseManager;

@Configuration
public class DatabaseConfiguration {
    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    public DatabaseManager DatabaseManager() {
        DatabaseManager databaseManager = new DatabaseManager();
        return databaseManager;
    }
}
