package com.abc.abc.Controler;

import java.text.SimpleDateFormat;
import java.util.List;


import com.abc.abc.Repository.PostRepository;
import com.abc.abc.Model.DataQuery;
import com.abc.abc.Model.Post;
import com.abc.abc.Service.JobPath;
import com.abc.abc.Service.PageManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;


/**
 * DataControler
 */
@Controller
public class MainController {


    @Autowired
    private PostRepository postRepository;

    @GetMapping({"","home","recent-post","recent-post/{page}"})
    public ModelAndView getPage(
        @PathVariable(value="page",required = false) String strPageNumber){

        String page = (strPageNumber== null)? "1":strPageNumber;
        ModelAndView mv = new ModelAndView("index");

        List<Post> postList = postRepository.findAll();
        List<String> companyList = postRepository.findCompanys();
        List<String> titleList = postRepository.findTitles();
        
        mv.addObject("companyList", companyList);
        mv.addObject("titleList", titleList);
        mv.addObject("postList", PageManagement.getListByPage(page, postList));   
        mv.addObject("dataSize",PageManagement.getSize(postList));       
        mv.addObject("page",page);

        mv.addObject("path", "recent-post");
        return mv;
    }
    

    @GetMapping({"nganh/{jobPath}","nganh/{jobPath}/{page}"})
    public ModelAndView getByJobName(
        @PathVariable(value = "jobPath", required = false) String jobPath,
        @PathVariable(value = "page", required = false) String strPage,

        @RequestParam(value = "title", defaultValue = "") String title,
        @RequestParam(value = "jobName", defaultValue = "") String jobName,
        @RequestParam(value = "location", defaultValue = "") String location,
        @RequestParam(value = "type", defaultValue = "") String type,
        @RequestParam(value = "experience", defaultValue = "") String experience,
        @RequestParam(value = "salary", defaultValue = "") String salary,
        @RequestParam(value = "company", defaultValue = "") String company
        ){
        ModelAndView mv = new ModelAndView("index");

        String page = (strPage == null)?"1":strPage;

        List<Post> postList = postRepository.getJobCustom(title, jobName, location, type, experience, salary, company);

        List<String> companyList = postRepository.findCompanys();
        List<String> titleList = postRepository.findTitles();

        mv.addObject("companyList", companyList);
        mv.addObject("titleList", titleList);
        mv.addObject("postList", PageManagement.getListByPage(page, postList));
        mv.addObject("dataSize", PageManagement.getSize(postList));
        mv.addObject("page", page);
        mv.addObject("path", "nganh/"+jobPath);

        mv.addObject("dataQuery", new DataQuery(title, type, experience, jobName, location, salary, company));
        return mv;
    }

    @GetMapping({"filter","filter/{page}"})
    public ModelAndView filter(
        @PathVariable(value = "page", required = false) String strPage,

        @RequestParam(value = "title", defaultValue = "") String title,
        @RequestParam(value = "jobName", defaultValue = "") String jobName,
        @RequestParam(value = "location", defaultValue = "") String location,
        @RequestParam(value = "type", defaultValue = "") String type,
        @RequestParam(value = "experience", defaultValue = "") String experience,
        @RequestParam(value = "salary", defaultValue = "") String salary,
        @RequestParam(value = "company", defaultValue = "") String company
        
    ){
        ModelAndView mv = new ModelAndView("index");

        if(JobPath.getPathByName(jobName) == null)
        {
            List<Post> postList = postRepository.getJobCustom(title, jobName, location, type, experience, salary, company);
            String page = (strPage == null) ? "1" : strPage;
            
            mv.addObject("postList", PageManagement.getListByPage(page,postList));
            mv.addObject("dataSize", PageManagement.getSize(postList));
            mv.addObject("page", page);
            mv.addObject("path", "filter");
            mv.addObject("dataQuery", new DataQuery(title, type, experience, jobName, location, salary, company));
        }
        else{
            mv.setViewName("redirect:/nganh/"+JobPath.getPathByName(jobName));

            mv.addObject("title",title);
            mv.addObject("jobName", jobName);
            mv.addObject("location", location);
            mv.addObject("type", type);
            mv.addObject("experience", experience);
            mv.addObject("salary", salary);
            mv.addObject("company", company);
        }
        return mv;
    }

   

    @GetMapping({ "profile-management", "profile-management/posts", "profile-management/posts/{page}" })
    public ModelAndView getAll(@PathVariable(value = "page", required = false) String strPageNumber) {

        String page = (strPageNumber == null) ? "1" : strPageNumber;

        ModelAndView mv = new ModelAndView("profile-management");
        List<Post> all = postRepository.findAll();
        mv.addObject("all", PageManagement.getListByPage(page, all));
        mv.addObject("dataSize", PageManagement.getSize(all));
        mv.addObject("page", page);
        mv.addObject("section", "posts");
        return mv;
    }

    @GetMapping("profile-management/your-posts")
    public ModelAndView getPost() {
        ModelAndView mv = new ModelAndView("profile-management");
        List<Post> unapprovedPosts = postRepository.getUnapprovedPosts();
        List<Post> approvedPosts = postRepository.getApprovedPosts();
        mv.addObject("unapprovedPosts", unapprovedPosts);
        mv.addObject("approvedPosts", approvedPosts);
        mv.addObject("section", "your-posts");
        return mv;
    }

    @GetMapping("profile-management/your-applications")
    public ModelAndView getApplications(String userId) {
        ModelAndView mv = new ModelAndView("profile-management");
        List<Post> applications = postRepository.getApplications(userId);
        mv.addObject("applications", applications);
        mv.addObject("section", "your-applications");

        return mv;
    }

    @GetMapping("post-number/{id}")
    public ModelAndView redirectPost(@PathVariable("id") String postId) {

        Post post = postRepository.getPostById(postId);
        if(post != null)
            return new ModelAndView("redirect:/post/"+JobPath.getPathByLabel(post.getLabel())+"/"+postId);
        else 
            return new ModelAndView("redirect:/home");
    }

    @GetMapping("post/{jobPath}/{id}")
    public ModelAndView getPostSingle(
        @PathVariable("id") String postId,
        @PathVariable("jobPath") String jobPath
        ){
        ModelAndView mv = new ModelAndView("job-single");
        Post post = postRepository.getPostById(postId);
        List<Post> relatedPosts = postRepository.findPostByLabel(JobPath.getLabelByPath(jobPath));

        relatedPosts.remove(post);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String postDate = formatter.format(post.getPostDate());
        String expiration = formatter.format(post.getExpiration());

        mv.addObject("jobName", JobPath.getNameByPath(jobPath));
        mv.addObject("post", post);
        mv.addObject("relatedPosts", relatedPosts);
        mv.addObject("postDate", postDate);
        mv.addObject("expiration", expiration);
        return mv;
    }

    // @GetMapping("apply/{id}")
    // public ModelAndView apply(@PathVariable("id") String postId) {
    //     ModelAndView mv = new ModelAndView("job-single");
    //     Post post = postRepository.getPostById(postId);
    //     List<Post> relatedPosts = postRepository.findPostByLabel(JobPath.getLabelByPath(jobPath));

    //     relatedPosts.remove(post);

    //     SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    //     String postDate = formatter.format(post.getPostDate());
    //     String expiration = formatter.format(post.getExpiration());

    //     mv.addObject("jobName", JobPath.getNameByPath(jobPath));
    //     mv.addObject("post", post);
    //     mv.addObject("relatedPosts", relatedPosts);
    //     mv.addObject("postDate", postDate);
    //     mv.addObject("expiration", expiration);
    //     return mv;
    // }
}