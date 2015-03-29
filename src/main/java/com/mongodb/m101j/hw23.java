/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



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
import org.bson.types.ObjectId;


public class hw23 {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("students");
        MongoCollection<Document>  collection = database.getCollection("grades");
    
        // insert 10 documents with a random integer as the value of field "x"
  
        
      //  Bson filter = new Document("x",0).append("y", new Document("$gt",50) );
        

        //Bson filter = new Document("x",0).append("y", new Document("$gt",10).append("$lt",60));
                
        // Bson filter = and(eq("x",0),gt("y",10),lt("y",90));        
        // Bson projection =  new Document("x",0).append("_id", 0);
            
        // Bson projection  = Projections.exclude("x","_id");
   
       
      // Bson projection =
      //      fields(
      //       include("y","i"),
      //       exclude("_id"));
        
        
      
        

       Bson projection  = fields(include("student_id","score","type"));
       Bson sort =  orderBy(ascending("student_id"),descending("score"));
       
       List<Document>  all = collection.find().projection(projection).sort(sort).into(new ArrayList <Document> ());
       
             
        Double prev_id= 0.0;
        Document prev;
        
        ObjectId curobj_id = null;
        prev = null;
        for (Document cur: all )
        {
            printJson.printJson(cur);       
            
           
            
            Double cur_id = cur.getDouble("student_id");
                            
                
            int retval = Double.compare(cur_id, prev_id);
            if (retval > 0)  // breakpoint
            {
              System.out.println(prev_id);
              prev_id = cur_id;
              
              System.out.println("will be removed");
              printJson.printJson(prev);  // to be removed
              System.out.println("will be removed");
              collection.deleteOne(prev);
            }
                
             prev = cur;
            
            
        } 
        
             System.out.println("will be removed");
              printJson.printJson(prev);  // to be removed
              System.out.println("will be removed");
        
        collection.deleteOne(prev);
         
        
        
    }
}


