/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package course.homework.w31;

/**
 *
 * @author izabelaskibinska
 */



import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import java.util.Date;
import org.bson.Document;

import java.util.List;
import org.bson.conversions.Bson;

public class BlogPostDAO {
    MongoCollection<Document> postsCollection;

    public BlogPostDAO(final MongoDatabase blogDatabase) {
        postsCollection = blogDatabase.getCollection("posts");
    }

    // Return a single post corresponding to a permalink
    public Document findByPermalink(String permalink) {

        // XXX HW 3.2,  Work Here
                
        Bson filter =  Filters.eq("permalink", permalink);
        Document post =   postsCollection.find(filter).first();

        return post;
    }

    // Return a list of posts in descending order. Limit determines
    // how many posts are returned.
    public List<Document> findByDateDescending(int limit) {

        // XXX HW 3.2,  Work Here
        // Return a list of DBObjects, each one a post from the posts collection
        List<Document> posts = null;
        
        
        
         posts = postsCollection.find().sort(new Document("date", -1)).limit(limit).into(new ArrayList<Document>());

        return posts;
    }

    
    public String addPost(String title, String body, List tags, String username) {

        System.out.println("inserting blog entry " + title + " " + body);

        String permalink = title.replaceAll("\\s", "_"); // whitespace becomes _
        permalink = permalink.replaceAll("\\W", ""); // get rid of non alphanumeric
        permalink = permalink.toLowerCase();


        // XXX HW 3.2, Work Here
        // Remember that a valid post has the following keys:
        // author, body, permalink, tags, comments, date
        //
        // A few hints:
        // - Don't forget to create an empty list of comments
        // - for the value of the date key, today's datetime is fine.
        // - tags are already in list form that implements suitable interface.
        // - we created the permalink for you above.

        // Build the post object and insert it
        
       
        Document post = new Document();

         post.append("title", title);
          post.append("author", username);
          
           post.append("body", body); 
           post.append("permalink", permalink); post.append("tags", tags);
           ArrayList commentList = new ArrayList(); 
           post.append("comments", commentList); 
           post.append("date", new Date()); 
           postsCollection.insertOne(post); 
        

        return permalink;
    }




    // White space to protect the innocent








    // Append a comment to a blog post
    public void addPostComment(final String name, final String email, final String body,
                               final String permalink) {

        // XXX HW 3.3, Work Here
        
          Document comment = new Document().append("body",body).append("author",name);
        
           if (email != null && !email.isEmpty()) {
             comment.append("email", email);
             }
        
           
           // find the post by permalink
                      
          // Bson filter =  Filters.eq("permalink", permalink);
          // Document post =   postsCollection.find(filter).first();
                   
          //Double  id = post.getDouble("_id");
          
          
           postsCollection.updateOne(new Document("permalink", permalink), new Document("$push", 
                new Document("comments", comment)));
          
          
        // Hints:
        // - email is optional and may come in NULL. Check for that.
        // - best solution uses an update command to the database and a suitable
        //   operator to append the comment on to any existing list of comments
    }
}
