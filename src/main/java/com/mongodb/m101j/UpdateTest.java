/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mongodb.m101j;

 
import com.mongodb.*;
import com.mongodb.client.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.exclude;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;
import static com.mongodb.client.model.Sorts.orderBy;

import org.bson.Document;

import com.mongodb.m101j.util.helpers.*;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bson.conversions.Bson;


public class UpdateTest {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("course");
        MongoCollection<Document>  collection = database.getCollection("findWithSortTest");
    
        collection.drop();
  
        // insert 10 documents with a random integer as the value of field "x"
  
        
        for (int i=0; i<8;i++)  {
            

           
               collection.insertOne(new Document().append("_id",i).append("x",i));
               
           }
            
        
        
      //  Bson filter = new Document("x",0).append("y", new Document("$gt",50) );
        

        //Bson filter = new Document("x",0).append("y", new Document("$gt",10).append("$lt",60));
                
        // Bson filter = and(eq("x",0),gt("y",10),lt("y",90));        
        // Bson projection =  new Document("x",0).append("_id", 0);
            
        // Bson projection  = Projections.exclude("x","_id");
   
       
      // Bson projection =
      //      fields(
      //       include("y","i"),
      //       exclude("_id"));
        
        
       collection.deleteMany(gt("_id",4));
        
        
        
        for (Document cur: collection.find().into(new ArrayList<Document>() ) )
        {
            printJson.printJson(cur);
        }    
         
        
        
    }
}


