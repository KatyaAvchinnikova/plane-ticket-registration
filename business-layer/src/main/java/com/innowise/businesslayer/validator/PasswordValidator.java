package com.innowise.businesslayer.validator;

import com.innowise.businesslayer.dto.user.UserRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
//Валидатор сделала, не подключала, для удобства тестирования
public class PasswordValidator implements Validator {

    private final String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

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

        if (!password.matches(regex)) {
            errors.rejectValue("password",
                    "Password must contain: one upper-case symbol, one lower-case symbol, one number and one special symbol. Password length cannot be less than 8 symbols");
        }
    }

}
