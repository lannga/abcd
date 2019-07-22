package com.abc.abc.Controler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.abc.abc.Repository.PostRepository;
import com.abc.abc.Repository.UserRepository;
import com.abc.abc.Model.Post;
import com.abc.abc.Model.User;
import com.abc.abc.Validate.RegisterValidate;
import com.abc.abc.Validate.newPostValidate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * FormControler
 */
@Controller
public class FormController {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("register")
    public ModelAndView getRegister(){
        ModelAndView mv = new ModelAndView("register");
        return mv;
    }

    @PostMapping("register")
    public ModelAndView postRegister(
        @RequestParam(value = "email", defaultValue = "") String email,
        @RequestParam(value = "password", defaultValue = "") String password
        ){
        ModelAndView mv = new ModelAndView("register");
        boolean hasEmail = userRepository.hasEmail(email);
        List<String> errors = RegisterValidate.getErrors(email, password, hasEmail);
        User user = new User(email,password);
        if(errors.size()>0){
            mv.addObject("errors", errors);
            mv.addObject("user", user);
        }
        else{
            userRepository.insert(user);
            mv.setViewName("redirect:/profile-management");
        }

        return mv;
    }



    @GetMapping("login")
    public ModelAndView getLogin() {
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

    @PostMapping("login")
    public ModelAndView postLogin() {
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }



    @GetMapping("new-post")
    public ModelAndView getNewPost() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("new-post");
        return mv;
    }

    @PostMapping("new-post")
    public ModelAndView postNewPost(
        @RequestParam(value = "title", defaultValue = "") String title,
        @RequestParam(value = "jobName", defaultValue = "") String jobName,
        @RequestParam(value = "company", defaultValue = "") String company,
        @RequestParam(value = "description", defaultValue = "") String description,
        @RequestParam(value = "requirement", defaultValue = "") String requirement,
        @RequestParam(value = "salary", defaultValue = "") String salary,
        @RequestParam(value = "experience", defaultValue = "") String experience,
        @RequestParam(value = "type", defaultValue = "") String type,
        @RequestParam(value ="location", defaultValue = "") List<String> locations
    ){

        String userId = "1";
                
        ModelAndView mv = new ModelAndView("new-post");
        List<String> errors = newPostValidate.getErrors(title, description,company,requirement); 
        Post post = new Post(jobName, title, description, requirement, type, experience, company, locations);       
        if(errors.size() > 0){
            mv.addObject("errors", errors);
            mv.addObject("post", post);            
        }
        else{
            SimpleDateFormat format = new SimpleDateFormat();
            format.setTimeZone(TimeZone.getTimeZone("UTC"));
            // Date date = new Date();

            // post.setPostDate(date);          
            post.setStatus("unapproved");
            post.setOwner(userId);
            postRepository.insert(post);
            mv.setViewName("redirect:/profile-management"); 

        }            
        return mv;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public void handleMissingParams(MissingServletRequestParameterException ex) {
        
    }
    
}