package com.example.db;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.WriteResultChecking;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;



@Configuration
@EnableMongoRepositories
public class ApplicationConfig extends AbstractMongoConfiguration {

  @Override
  public Mongo mongo() throws Exception {
    Mongo mongo = new MongoClient();
    mongo.setWriteConcern(WriteConcern.SAFE);
    return mongo;
  }

  public MongoTemplate mongoTemplate() throws Exception {
    MongoTemplate mongoTemplate = new MongoTemplate(mongo(), getDatabaseName());
    mongoTemplate.setWriteResultChecking(WriteResultChecking.EXCEPTION);
    return mongoTemplate;
  }
  
  @Override
  protected String getDatabaseName() {
    return "springdata";
  }
}