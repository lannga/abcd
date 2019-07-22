package com.abc.abc.Repository;


import java.util.ArrayList;
import java.util.List;

import com.abc.abc.Model.Post;
import com.abc.abc.Service.Process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

/**
 * PostRepository
 */
@Component
public class PostRepository {
    
    @Autowired
    private MongoTemplate mongoTemplate;

    private static String collection = "post";


    // insert bài đăng mới
    public void insert(Post post){
        mongoTemplate.insert(post,collection);
    }

    //lấy bài chưa được xác thực
    public List<Post> getUnapprovedPosts(){
        Query query = new BasicQuery("{'status': 'unchecked'}");
        List<Post> unapprovedPosts = mongoTemplate.find(query, Post.class,collection);
        return unapprovedPosts;
    }

    // lấy bài đã xác thực
    public List<Post> getApprovedPosts() {
        Query query = new BasicQuery("{'status': 'checked'}");
        List<Post> approvedPosts = mongoTemplate.find(query, Post.class, collection);
        return approvedPosts;
    }

    // Lấy bài đã ứng tuyển
    public List<Post> getApplications(String userId){
        Query query = new BasicQuery("{'applicators' : '"+ userId +"'}");
        List<Post> applications = mongoTemplate.find(query, Post.class,collection);
        return applications;
    }

    //Lấy bài bằng id
    public Post getPostById(String postId){
        Query query = new BasicQuery("{'_id' : '"+ postId +"'}");
        Post post = mongoTemplate.findOne(query, Post.class);
        return post;
    }

    public List<String> findCompanys() {
        List<String> companys = mongoTemplate.findDistinct("company", Post.class, String.class);
        return companys;
    }

    public List<String> findTitles() {
        List<String> titles = mongoTemplate.findDistinct("title", Post.class, String.class);
        return titles;
    }

    public List<Post> getJobCustom(String title, String job_name, String location, String type, String experience,
            String salary, String company) {
        String query = Process.convert_all_to_query(title, job_name, location, type, experience, salary, company);
        Query query_custom = new BasicQuery(query);
        query_custom = query_custom.with(new Sort(Sort.Direction.DESC, "post_date"));
        List<Post> listDoc = new ArrayList<Post>();
        listDoc.addAll(mongoTemplate.find(query_custom, Post.class, collection));
        return listDoc;
    }

    public List<Post> findAll(){
        return getJobCustom("", "", "", "", "", "", "");
    }

    public List<Post> findPostByLabel(int label) {
        Query query = new BasicQuery("{'label': "+label+"}");
        query = query.with(new Sort(Sort.Direction.DESC, "post_date")).limit(10);
        List<Post> posts = mongoTemplate.find(query, Post.class, collection);
        return posts;
    }
    
    

}

