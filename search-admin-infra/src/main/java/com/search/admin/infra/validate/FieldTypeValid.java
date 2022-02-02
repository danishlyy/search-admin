package com.search.admin.infra.validate;

import com.search.admin.infra.validate.impl.FieldTypeValidValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = FieldTypeValidValidator.class)
public @interface  FieldTypeValid {

    String message() default "field的类型不合法";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
