package com.abc.abc.Validate;

import java.util.LinkedList;
import java.util.List;

/**
 * RegisterValidate
 */
public class RegisterValidate {

    public static List<String> getErrors(String email, String password,boolean hasEmail) {
        List<String> errors = new LinkedList<>();

        if (hasEmail)
            errors.add("Email đã tồn tại");
        if (email.equals(""))
            errors.add("Email chưa được nhập");
        if (password.length() < 7)
            errors.add("Mật khẩu phải >= 6 kí tự");
        return errors;
    }
}