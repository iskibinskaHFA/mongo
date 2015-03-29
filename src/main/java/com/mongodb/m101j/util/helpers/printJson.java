/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



package com.mongodb.m101j.util.helpers;

import org.bson.Document;
import org.bson.codecs.DocumentCodec;
import org.bson.codecs.EncoderContext;
import java.io.StringWriter;
import org.bson.json.*;

/**
 *
 * @author izabelaskibinska
 */
public class printJson {
    
    public static void printJson(Document document)
    {
        JsonWriter jsonWriter =  new JsonWriter(new StringWriter(),
                                                new JsonWriterSettings(JsonMode.SHELL,false));
        
        new DocumentCodec().encode(jsonWriter,document,
                                    EncoderContext.builder()
                                    .isEncodingCollectibleDocument(true)
                                    .build());
                
            
        System.out.println(jsonWriter.getWriter());        
        System.out.flush();
        
        
    }        
    
    
}
