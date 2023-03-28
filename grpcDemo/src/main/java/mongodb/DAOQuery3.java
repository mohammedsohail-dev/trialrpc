package org.example.mongodb;


import com.mongodb.ConnectionString;
import com.mongodb.DBCollection;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertManyResult;
import com.mongodb.client.result.UpdateResult;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.logging.Logger;


public class DAOQuery3 {
    

    ConnectionString connectionString = new ConnectionString("mongodb+srv://sohail179:URGr3Q!AyR4kyq4@cluster0.ggny9n6.mongodb.net/skiers_resorts?retryWrites=true&w=majority");
MongoClientSettings settings = MongoClientSettings.builder()
        .applyConnectionString(connectionString)
        .applyToSocketSettings(builder -> builder.connectTimeout(60000, TimeUnit.MILLISECONDS).build())
        .build();
MongoClient mongoClient = MongoClients.create(settings);
MongoDatabase database = mongoClient.getDatabase("skiers_resorts");

public List<String[]> getEconomicstates(String Year,String Type,String Length) {
    MongoCollection<Document> collection = database.getCollection("EduCoststat");
    MongoCollection<Document> collection2 = database.getCollection("EduCostStatQueryThree");
    ArrayList<String> states = new ArrayList<>();
    states.add("Alabama");
    states.add("Alaska");
    states.add("Arizona");
    states.add("Arkansas");
    states.add("California");
    states.add("Colorado");
    states.add("Connecticut");
    states.add("Delaware");
    states.add("Florida");
    states.add("Georgia");
    states.add("Hawaii");
    states.add("Idaho");
    states.add("Illinois");
    states.add("Indiana");
    states.add("Iowa");
    states.add("Kansas");
    states.add("Kentucky");
    states.add("Louisiana");
    states.add("Maine");
    states.add("Maryland");
    states.add("Massachusetts");
    states.add("Michigan");
    states.add("Minnesota");
    states.add("Mississippi");
    states.add("Missouri");
    states.add("Montana");
    states.add("Nebraska");
    states.add("Nevada");
    states.add("New Hampshire");
    states.add("New Jersey");
    states.add("New Mexico");
    states.add("New York");
    states.add("North Carolina");
    states.add("North Dakota");
    states.add("Ohio");
    states.add("Oklahoma");
    states.add("Oregon");
    states.add("Pennsylvania");
    states.add("Rhode Island");
    states.add("South Carolina");
    states.add("South Dakota");
    states.add("Tennessee");
    states.add("Texas");
    states.add("Utah");
    states.add("Vermont");
    states.add("Virginia");
    states.add("Washington");
    states.add("West Virginia");
    states.add("Wisconsin");
    states.add("Wyoming");

    
    
    
    List<String[]> listfinal = new ArrayList<>();
    List<String[]> sortedlist = new ArrayList<>();
    for(int i=0;i<states.size();i++){
    Document doc1 = new Document().append("year",Year).append("type",Type).append("length",Length).append("state",states.get(i));
    Document projection = new Document("value",1).append("_id",0);
    FindIterable<Document> iterable = collection.find(doc1).projection(projection);
    List<Document> users = new ArrayList<>();
    for (Document user : iterable) {
        users.add(user);
    }

    Integer total=0;

    for(int j=0;j<users.size();j++){
       total=Integer.parseInt((users.get(j).get("value")).toString())+total;



    }

    String[] row = new String[] {states.get(i),total.toString()};

    listfinal.add(row);

    
        


    }


    Collections.sort(listfinal , new Comparator<String[]>() {
        public int compare(String[] s1, String[] s2) {
            Integer i1 = Integer.parseInt(s1[1]);
            Integer i2 = Integer.parseInt(s2[1]);
            return i1.compareTo(i2);
        }
    });

    for (String[] state : listfinal) {
        sortedlist.add(state);
    }
    List<String[]> Answer =sortedlist.subList(0, 5);
    
    Document doc = new Document().append("year",Year).append("type",Type).append("length",Length).append("state_1", Answer.get(0)[0]).append("state_2", Answer.get(1)[0]).append("state_3", Answer.get(2)[0]).append("state_4", Answer.get(3)[0]).append("state_5", Answer.get(4)[0]); 
    
    
    collection2.insertOne(doc);
    


    return Answer;

    
}

   
    

    
}
