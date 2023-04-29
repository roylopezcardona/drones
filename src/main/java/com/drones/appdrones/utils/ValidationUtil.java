package com.drones.appdrones.utils;

import com.drones.appdrones.domain.validation.CreateValidationGroup;
import com.drones.appdrones.domain.validation.UpdateValidationGroup;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Set;

public class ValidationUtil {

    public static void validate(final Object object, boolean create) {
        final ArrayList<Class<?>> validationGroups = new ArrayList<>();
        validationGroups.add(create ? CreateValidationGroup.class : UpdateValidationGroup.class);
        final Class<?>[] validationGroupsArray = validationGroups.toArray(new Class<?>[validationGroups.size()]);

        final Validator validator = javax.validation.Validation.buildDefaultValidatorFactory().getValidator();
        final Set<ConstraintViolation<Object>> violations = validator.validate(object, validationGroupsArray);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

}
