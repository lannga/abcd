package com.abc.abc.Repository;

import java.util.List;

import com.abc.abc.Model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

/**
 * UserRepository
 */

@Component
public class UserRepository {

    @Autowired
    private MongoTemplate mongoTemplate;
    private static String collection = "user";

    public boolean hasEmail(String email) {
        Query query = new BasicQuery("{'email':'"+email+"'}");
        List<String> emails = mongoTemplate.findDistinct(query, "email", User.class, String.class);
        if(emails.size()>0) return true;
        else return false;
    }

    public void insert(User user){
        mongoTemplate.insert(user, collection);
    }
    
}