package com.abc.abc.Validate;

import java.util.LinkedList;
import java.util.List;

/**
 * LoginValidate
 */
public class LoginValidate {

    public static List<String> getErrors(boolean hasEmail,boolean correctPassword) {
        List<String> errors = new LinkedList<>();
        if (!hasEmail)
            errors.add("Email không tồn tại");
        if(!correctPassword)
            errors.add("Sai mật khẩu");
        return errors;
    }
}