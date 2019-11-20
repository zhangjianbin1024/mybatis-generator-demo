package com.myke.framework.validator;


import com.myke.framework.constant.SysErrorCodeEnum;

/**
 * 参数异常
 */
public class ApiParamException extends RuntimeException {

    /**
     * 错误码
     */
    private Integer code;

    private BeanValidateResult beanValidateResult;


    public ApiParamException(Integer code, String message) {
        super(message);
        this.code = SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode();
    }


    public ApiParamException(String message) {
        super(SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getMessage() + ":" + message);
        this.code = SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode();
    }

    public ApiParamException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiParamException(Throwable cause) {
        super(cause);
    }

    public BeanValidateResult getBeanValidateResult() {
        return beanValidateResult;
    }
}
