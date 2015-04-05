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
import java.util.Iterator;
import java.util.List;

import org.bson.conversions.Bson;
import org.bson.types.ObjectId;


public class hw31 {
    
    
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("school");
        MongoCollection<Document>  collection = database.getCollection("students");
    
    
        

       Bson projection  = fields(include("_id","name","scores"));
       Bson sort =  orderBy(ascending("_id"));
       
       List <Document>  all = collection.find().projection(projection).sort(sort).into(new ArrayList <Document> ());
       
             
        Double prev_id= 0.0;
        Document prev;
        
        //ObjectId curobj_id = null;
        //prev = null;
        
        for (Document cur: all )
        {
            
       
            // printJson.printJson(cur);       
                                  
             Double cur_id = cur.getDouble("_id");
             String name = cur.getString("name");
              Double lowscore = 0.0;
              List <Document> scores =  (List) cur.get("scores");              
              {
                  
                 for (Document curscore : scores )
                 {                        
                    lowscore = 0.0;                      
                      if("homework".equals(curscore.get("type")))
                      {                           
                           double  sc = curscore.getDouble("score");                           
                           if (lowscore == 0.0) {
                                  lowscore = sc;
                            }
                                                      
                           if (sc < lowscore)
                               lowscore = sc;         
                       }                           
                 }
                 
                 Iterator iterator = scores.iterator();
                 while (iterator.hasNext()) {
                    Document doc  = (Document) iterator.next();
                    if ("homework".equals(doc.get("type"))) {
                        double score = doc.getDouble("score");
                            if (score == lowscore) {
                             iterator.remove();
                        }   
                     }
                   }
                 collection.replaceOne(eq("_id",cur_id), new Document("_id",cur_id).append("name",name).append("scores",scores)) ;
                
                 System.out.println(lowscore);
                 
               }
            
        } 
       
    }
}


