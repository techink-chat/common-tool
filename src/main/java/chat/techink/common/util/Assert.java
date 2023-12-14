/*
 * Copyright 2002-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package chat.techink.common.util;

import chat.techink.common.error.BusinessException;
import chat.techink.common.error.code.ResultCode;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Set;
import java.util.function.Supplier;


/**
 * @author xujianxing
 */
public abstract class Assert {

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();


    public static <T> void validateBean(T bean, ResultCode resultCode) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(bean);
        if (!constraintViolations.isEmpty()) {
            String detail = constraintViolations.iterator().next().getMessage();
            throw new BusinessException(resultCode, detail);
        }
    }

    public static void notNull(Object value, ResultCode resultCode,
                               String... format) {
        if (value == null) {
            throw new BusinessException(resultCode, String.format(resultCode.detail(), format));
        }
    }

    public static void notNull(Object value, ResultCode resultCode, String detail) {
        if (value == null) {
            throw new BusinessException(resultCode.title(), detail, resultCode);
        }
    }


    public static void hasLength(String text, ResultCode resultCode, String detail) {
        if (StringUtils.isBlank(text)) {
            throw new BusinessException(resultCode, detail);
        }
    }


    public static void hasText(String text, ResultCode resultCode, String... format) {
        if (StringUtils.isBlank(text)) {
            throw new BusinessException(resultCode, String.format(resultCode.detail(), format));
        }
    }

    public static void hasText(String text, Supplier<BusinessException> exceptionSupplier) {
        if (StringUtils.isBlank(text)) {
            throw exceptionSupplier.get();
        }
    }


    public static void isTrue(boolean value, Supplier<BusinessException> exceptionSupplier) {
        if (!value) {
            throw exceptionSupplier.get();
        }
    }

    public static void isTrue(boolean value, BusinessException error) {
        if (!value) {
            throw error;
        }
    }

    public static void isTrue(boolean value, ResultCode resultCode, String detail) {
        if (!value) {
            throw new BusinessException(resultCode, detail);
        }
    }

    public static void notEmpty(Collection<?> collection, ResultCode resultCode, String detail) {
        if (collection == null || collection.isEmpty()) {
            throw new BusinessException(resultCode, detail);
        }
    }

    public static void notEmpty(Collection<?> collection, ResultCode resultCode) {
        if (collection == null || collection.isEmpty()) {
            throw new BusinessException(resultCode);
        }
    }

    public static void notEmpty(Collection<?> collection, ResultCode resultCode, String detail, String... format) {
        if (collection == null || collection.isEmpty()) {
            throw new BusinessException(resultCode, String.format(detail, format));
        }
    }

    public static void empty(Collection<?> collection, ResultCode resultCode, String detail) {
        boolean empty = (collection == null || collection.isEmpty());
        if (!empty) {
            throw new BusinessException(resultCode, detail);
        }
    }


    public static void maxLength(String text, int maxLength, ResultCode resultCode, String... format) {
        if (text.length() > maxLength) {
            throw new BusinessException(resultCode, String.format(resultCode.detail(), format));
        }
    }
}
