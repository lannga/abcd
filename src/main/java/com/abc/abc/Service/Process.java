package com.abc.abc.Service;

import java.util.Map;
import java.util.TreeMap;

public class Process {

    public Process() {
        // TODO Auto-generated constructor stub
    }

    public static String check_null(String input) {
        String str = "";
        if (input != null)
            str = input;
        return str;
    }

    public static String convert_to_string_query(String field_name, String field_input, String operator) {
        String query_field = "";
        if (field_input != null) {
            query_field = "\"" + field_name + "\"" + ":{" + operator + ":\"" + field_input + "\",$options: \"i\"},";

        }
        return query_field;
    }

    public static String convert_to_normal_query(String field_name, String field_input, String operator) {
        String query_field = "";
        if (field_input != null) {
            query_field = "\"" + field_name + "\"" + ":{" + operator + ":" + field_input + "},";
        }
        return query_field;
    }

    public static String process_job_name(String job_name) {
        String job_label = null;
        switch (job_name) {
        case "Kiến trúc":
            job_label = "1";
            break;
        case "Ngân hàng":
            job_label = "2";
            break;
        case "Biên dịch":
            job_label = "3";
            break;
        default:
            break;
        }

        return job_label;
    }

    public static String process_job_name_to_query(String job_name) {
        if (job_name == null || job_name.equals("Tất cả các ngành")) {
            return "";
        }
        String job_label = process_job_name(job_name);
        String job_name_query = convert_to_normal_query("label", job_label, "$eq");
        return job_name_query;
    }

    public static String process_location_to_query(String location) {
        if (location == null || location.equals("Mọi nơi")) {
            return "";
        }
        String input_str = "{$regex:\"" + location + "\"}";
        String job_name_query = convert_to_normal_query("work_place_list", input_str, "$elemMatch");
        return job_name_query;
    }

    public static String process_type_to_query(String type) {
        if (type == null) {
            return "";
        }
        return convert_to_string_query("type", type, "$regex");
    }

    public static String process_experience_to_query(String experience) {
        if (experience == null || experience.equals("Tất cả"))  {
            return "";
        }
        return convert_to_string_query("experience", experience, "$regex");
    }

    public static String process_company_to_query(String company) {
        if (company == null) {
            return "";
        }
        return convert_to_string_query("company", company, "$regex");
    }

    public static String process_title_to_query(String title) {
        if (title == null) {
            return "";
        }
        return convert_to_string_query("title", title, "$regex");
    }

    public static Map<String, String> process_salary(String salary) {
        Map<String, String> salary_map = new TreeMap<String, String>();
        String salary_min = null;
        String salary_max = null;

        switch (salary) {
        case "Dưới 1 triệu":
            salary_max = "1";
            break;
        case "Trên 50 triệu":
            salary_min = "50";
            break;
        case "Tất cả":
            break;
        case "":
            break;
        default:

            String[] word = salary.split("\\s");
            salary_min = word[0];
            salary_max = word[2];
            break;
        }
        salary_map.put("salary_min", salary_min);
        salary_map.put("salary_max", salary_max);
        return salary_map;
    }

    public static String process_salary_to_query(String salary) {
        if (salary == null) {
            return "";
        }
        Map<String, String> salary_map = process_salary(salary);
        String salary_min = salary_map.get("salary_min");
        String salary_max = salary_map.get("salary_max");
        String salary_min_query = convert_to_normal_query("salary.min", salary_min, "$gte");
        String salary_max_query = convert_to_normal_query("salary.max", salary_max, "$lte");
        return salary_min_query + salary_max_query;
    }

    public static String convert_all_to_query(String title,String job_name, String location, String type, String experience,
            String salary, String company) {
        String query_str = null;
        String query = "";

        String title_query = process_title_to_query(title);

        String job_name_query = process_job_name_to_query(job_name);

        String location_query = process_location_to_query(location);

        String type_query = process_type_to_query(type);

        String experience_query = process_experience_to_query(experience);
        String salary_query = /*process_salary_to_query(salary);*/"";
        String company_query = process_company_to_query(company);
        query_str = title_query + job_name_query + location_query + type_query + experience_query + salary_query + company_query;
        query = "{" + query_str + "}";
        return query;
    }
}