package com.example.businesslayernew.validator;

import com.example.businesslayernew.dto.user.UserRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PasswordValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserRequest request = (UserRequest) target;

        String password = request.getPassword();

        if (password.equals("") || password.equals(" ")) {
            errors.rejectValue("password", "Password shouldn't be empty or blank");
        }

        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
            errors.rejectValue("password", "Password must contain: one upper-case symbol, one lower-case symbol, one number and one special symbol. Password length cannot be less than 8 symbols");
        }
    }
}
