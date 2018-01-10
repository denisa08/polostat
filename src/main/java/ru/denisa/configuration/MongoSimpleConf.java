package ru.denisa.configuration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
 import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by d.aleksandrov on 24.08.2017.
 */
@Profile("dev")
@Configuration
public class MongoSimpleConf  extends AbstractMongoConfiguration {
    @Value("${spring.data.local.mongodb.host}")
    private String host;

    @Value("${spring.data.local.mongodb.port}")
    private Integer port;

    @Value("${spring.data.local.mongodb.database}")
    private String database;

    public @Bean
    MongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory(new MongoClient(this.host + ":" + this.port), database);
    }

    public @Bean
    MongoTemplate mongoTemplate() throws Exception {
        MappingMongoConverter converter = new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory()),
                new MongoMappingContext());
        converter.setMapKeyDotReplacement("\\+");

        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory(), converter);

        return mongoTemplate;

    }

    @Override
    protected String getDatabaseName() {
        return this.database;
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(this.host, this.port);
    }




}