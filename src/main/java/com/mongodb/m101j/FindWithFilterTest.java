/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mongodb.m101j;
/**
 *
 * @author izabelaskibinska
 */

import com.mongodb.*;
import com.mongodb.client.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.exclude;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;

import org.bson.Document;

import com.mongodb.m101j.util.helpers.*;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bson.conversions.Bson;

public class FindWithFilterTest {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("course");
        MongoCollection<Document>  collection = database.getCollection("findWithFilterTest");
    
        collection.drop();
  
        // insert 10 documents with a random integer as the value of field "x"
        for (int i = 0; i < 10; i++) {
            collection.insertOne(new Document("x",i));
            
           
            collection.insertOne(new Document() 
                    .append("x", new Random().nextInt(2))
                    .append("y", new Random().nextInt(100))
                    .append("i",i));
                    
                    
        }

        
      //  Bson filter = new Document("x",0).append("y", new Document("$gt",50) );
        

        //Bson filter = new Document("x",0).append("y", new Document("$gt",10).append("$lt",60));
                
        Bson filter = and(eq("x",0),gt("y",10),lt("y",90));        
        //Bson projection =  new Document("x",0).append("_id", 0);
            
       //Bson projection  = Projections.exclude("x","_id");
   
       
       Bson projection =
            fields(
             include("y","i"),
             exclude("_id"));
                          
       
       List<Document>  all = collection.find(filter).projection(projection).into(new ArrayList <Document> ());
        
        for (Document cur: all)
        {
            printJson.printJson(cur);
        }    
         
        System.out.println("\nCount:");
        
        
        long count = collection.count(filter);
        
        System.out.println(" Find all with iteration");
        
        MongoCursor <Document> cursor  = collection.find(filter).projection(projection).iterator();
        
        try {
            while (cursor.hasNext())  {
               
                Document cur = cursor.next();
                printJson.printJson(cur);                
            }
                
        } finally {
          cursor.close();   
        }
                
        System.out.println(count);
        
        count = collection.count();
        System.out.println(count);
        
    }
}


