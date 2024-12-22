package me.mikael.jooqtest.configuration;

import org.jooq.conf.Settings;
import org.springframework.boot.autoconfigure.jooq.DefaultConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
//@ComponentScan({"me.mikael.jooqtest.db.information_schema.public_.tables"})
@EnableTransactionManagement
public class ApplicationJooqConfiguration {

    @Bean
    public DefaultConfigurationCustomizer jooqConfigurationCustomizer() {
        return (config) -> {
            config.setSettings(
                    new Settings()
                            .withMapRecordComponentParameterNames(true)
            );
        };
    }
}
