/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mongodb.m101j;



import com.mongodb.*;
import com.mongodb.client.*;

import java.net.UnknownHostException;
import static java.util.Arrays.asList;
import org.bson.Document;

import com.mongodb.m101j.util.helpers.*;

public class InsertTest {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> coll = db.getCollection("insertTest");
        
        coll.drop();

        
        Document smith = new Document("name", "Smith")
                          .append("age",30)
                          .append("profession","programmer");
        
        
          Document jones = new Document("name", "Jones")
                          .append("age",50)
                          .append("profession","hacker");
        
        
        coll.insertMany(asList(smith,jones));
       // coll.insertOne(smith);
       

        printJson.printJson(smith);
     
    }
}
