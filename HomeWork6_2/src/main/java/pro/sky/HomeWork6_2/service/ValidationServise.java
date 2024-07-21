package pro.sky.HomeWork6_2.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.HomeWork6_2.exeption.ValidationFailedExeption;

@Service
public class ValidationServise {

    public String validateCheckName(String name) {
        name = name.trim();
        if (StringUtils.isAlpha(name)) {
            throw new ValidationFailedExeption();
        }
        return StringUtils.capitalize(name.toLowerCase());
    }

}
