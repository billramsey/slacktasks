package com.example.db;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.WriteResultChecking;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;



@Configuration
@EnableMongoRepositories
public class ApplicationConfig extends AbstractMongoConfiguration {

  @Override
  public Mongo mongo() throws Exception {
    Mongo mongo = new MongoClient();
    mongo.setWriteConcern(WriteConcern.SAFE);
    return mongo;
  }
/*
  @Override
  public MongoTemplate mongoTemplate() throws Exception {
    MongoTemplate mongoTemplate = new MongoTemplate(mongo(), getDatabaseName());
    mongoTemplate.setWriteResultChecking(WriteResultChecking.EXCEPTION);
    return mongoTemplate;
  }
*/
  @Override
  protected String getDatabaseName() {
    return "springdata";
  }

  @Override
  public MongoTemplate mongoTemplate() throws Exception {
  if (System.getenv("OPENSHIFT_MONGODB_DB_HOST") != null) {
      String dbhost = System.getenv("OPENSHIFT_MONGODB_DB_HOST");
      int dbport = Integer.parseInt(System.getenv("OPENSHIFT_MONGODB_DB_PORT"));
      String username = System.getenv("OPENSHIFT_MONGODB_DB_USERNAME");
      String password = System.getenv("OPENSHIFT_MONGODB_DB_PASSWORD");
      String dbname = System.getenv("OPENSHIFT_APP_NAME");
      
      MongoClient mongoclient = new MongoClient(Collections.singletonList(new ServerAddress(dbhost,dbport)),
          Collections.singletonList(MongoCredential.createCredential(username,dbname, password.toCharArray())));
      

      MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongoclient, dbname);
      MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory);
      return mongoTemplate;
         
    } else {
        return new MongoTemplate(mongo(), getDatabaseName());
    }
  }
  
  
}