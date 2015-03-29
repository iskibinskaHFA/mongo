package com.mongodb.m101j;


import java.util.Arrays;
import java.util.Date;
import org.bson.Document;

import  com.mongodb.m101j.util.helpers.printJson;
import org.bson.types.ObjectId;

public class DocumentTest {
    
   
    public static void main(String[] args) {

      Document document =  new Document()
              .append("str","MongoDB, Hello")
              .append("int",42)
              .append("l",1L)
              .append("double",1.1)
              .append("b",false) 
              .append("objectId",new ObjectId())
              .append("null",null)
              .append("embeddedDoc", new Document("x",0))
              .append("list",Arrays.asList(1,2,3));
              
              

              String str = document.getString("str");
              
              System.out.println(str);
              int i = document.getInteger("int");
      
      
                            
        
      
    }
    
    
}
