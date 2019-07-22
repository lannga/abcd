package com.abc.abc.Model;

import java.util.Date;
import java.util.List;

import com.abc.abc.Service.JobPath;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Post
 */
@Document(collection = "post")
public class Post {

    @Id
    @Field("_id")
    private String id;
    
    private String title;
    private String jobName;
    private String description;
    private String requirement;
    private String type;
    private String experience;
    private int label;
    @Field("work_place_list")
    private List<String> locations;
    @Indexed
    @Field("post_date")
    private Date postDate;
    private Date expiration;
    private String company;
    private String owner;
    private List<String> applicators;
    private String status;

    // public String url = "/nganh/" + JobPath.getPath(jobName) + "/" + id;

    public Post() {
    }

    public Post(String jobName, String title, String description, String requirement, String type, String experience,
            String company, List<String> locations) {
        this.jobName = jobName;
        this.title = title;
        this.description = description;
        this.requirement = requirement;
        this.type = type;
        this.experience = experience;
        this.company = company;
        this.locations = locations;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<String> getApplicators() {
        return applicators;
    }

    public void setApplicators(List<String> applicators) {
        this.applicators = applicators;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    
    
}