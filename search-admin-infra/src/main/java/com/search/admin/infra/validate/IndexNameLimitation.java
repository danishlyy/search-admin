package com.search.admin.infra.validate;

import com.search.admin.infra.validate.impl.IndexNameLimitationValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = IndexNameLimitationValidator.class)
public @interface IndexNameLimitation {

    String message() default "indexName is not correct,please refer to https://www.elastic.co/guide/en/elasticsearch/reference/7.0/indices-create-index.html";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
