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

import java.net.UnknownHostException;
import static java.util.Arrays.asList;
import org.bson.Document;

import com.mongodb.m101j.util.helpers.*;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FindTest {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("course");
        MongoCollection<Document>  collection = database.getCollection("findTest");
    
        collection.drop();

        // insert 10 documents with a random integer as the value of field "x"
        for (int i = 0; i < 10; i++) {
            collection.insertOne(new Document("x",i));
        }

        System.out.println("Find one:");          
        Document first = collection.find().first();
        
        printJson.printJson(first);
        
        System.out.println("Find all with into: ");
        List<Document>  all = collection.find().into(new ArrayList <Document> ());
        
        for (Document cur: all)
        {
            printJson.printJson(cur);
        }    
         
        System.out.println("\nCount:");
        
        
        long count = collection.count();
        
        System.out.println(" Find all with iteration");
        
        MongoCursor <Document> cursor  = collection.find().iterator();
        
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
