package com.myke.framework.validator;


import lombok.Data;

/**
 * 参数校验结果封装实体
 */
@Data
public class BeanValidateResult {

    private String fieldName;

    private String message;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
