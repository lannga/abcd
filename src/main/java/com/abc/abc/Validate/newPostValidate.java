package com.abc.abc.Validate;

import java.util.LinkedList;
import java.util.List;

/**
 * newPostValidate
 */
public class newPostValidate {

    public static List<String> getErrors(String title,String description,String company,String requirement){
        List<String> errors = new LinkedList<>();
        if(title.equals(""))
            errors.add("Tiêu đề chưa được nhập");
        if(description.equals(""))
            errors.add("Mô tả chưa được nhập");
        if (company.equals(""))
            errors.add("Tên công ty chưa được nhập"); 
        if (requirement.equals(""))
            errors.add("Yêu cầu chưa được nhập");
        return errors;
    }



}