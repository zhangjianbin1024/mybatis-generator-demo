package com.myke.framework.web.handler;

import com.myke.framework.constant.SysErrorCodeEnum;
import com.myke.framework.exception.ServiceException;
import com.myke.framework.validator.ApiParamException;
import com.myke.framework.validator.BeanValidateResult;
import com.myke.framework.vo.CommonResult;
import com.myke.framework.web.SupperController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * 只有继承 SupperController 类被该 @ControllerAdvice 管理
 */
@RestControllerAdvice(assignableTypes = SupperController.class)
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());


    // 逻辑异常
    @ResponseBody
    @ExceptionHandler(value = ServiceException.class)
    public CommonResult serviceExceptionHandler(HttpServletRequest req, ServiceException ex) {
        logger.debug("[serviceExceptionHandler]", ex);
        return CommonResult.error(ex.getCode(), ex.getMessage());
    }

    // Spring MVC 参数不正确
    @ResponseBody
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public CommonResult missingServletRequestParameterExceptionHandler(HttpServletRequest req, MissingServletRequestParameterException ex) {
        logger.warn("[missingServletRequestParameterExceptionHandler]", ex);
        return CommonResult.error(SysErrorCodeEnum.MISSING_REQUEST_PARAM_ERROR.getCode(), SysErrorCodeEnum.MISSING_REQUEST_PARAM_ERROR.getMessage() + ":" + ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = {ConstraintViolationException.class})
    public CommonResult constraintViolationExceptionHandler(HttpServletRequest req, ConstraintViolationException ex) {
        logger.info("[constraintViolationExceptionHandler]", ex);
        // 拼接详细报错
        StringBuilder detailMessage = new StringBuilder("\n\n详细错误如下：");
        ex.getConstraintViolations().forEach(constraintViolation -> detailMessage.append("\n").append(constraintViolation.getMessage()));
        return CommonResult.error(SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode(), SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getMessage()
                + detailMessage.toString());
    }

    /**
     * 如果参数前有 @requestBody 注解 校验失败时抛出下面的异常
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public CommonResult MethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        BeanValidateResult beanValidateResult = new BeanValidateResult();

        if (!fieldErrors.isEmpty()) {
            FieldError fieldError = fieldErrors.get(0);
            String field = fieldError.getField();
            String defaultMessage = fieldError.getDefaultMessage();
            beanValidateResult.setFieldName(field);
            beanValidateResult.setMessage(defaultMessage);
        }
        return CommonResult.error(SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode(),
                SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getMessage() + ":" + beanValidateResult);


    }

    /**
     * 参数 没有放在 body 中校验失败抛出下面的异常
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = {BindException.class})
    public CommonResult BindException(BindException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        BeanValidateResult beanValidateResult = new BeanValidateResult();

        if (!fieldErrors.isEmpty()) {
            FieldError fieldError = fieldErrors.get(0);
            String field = fieldError.getField();
            String defaultMessage = fieldError.getDefaultMessage();
            beanValidateResult.setFieldName(field);
            beanValidateResult.setMessage(defaultMessage);
        }
        return CommonResult.error(SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode(),
                SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getMessage() + ":" + beanValidateResult);


    }

    @ResponseBody
    @ExceptionHandler(value = ApiParamException.class)
    public CommonResult MethodArgumentNotValidException(ApiParamException ex) {
        return CommonResult.error(SysErrorCodeEnum.VALIDATION_REQUEST_PARAM_ERROR.getCode(), ex.getMessage());
    }

    /**
     * 默认异常处理
     *
     * @param req
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public CommonResult exceptionHandler(HttpServletRequest req, Exception e) {
        logger.error("[exceptionHandler]", e);
        // 返回 ERROR CommonResult
        return CommonResult.error(SysErrorCodeEnum.SYS_ERROR.getCode(), SysErrorCodeEnum.SYS_ERROR.getMessage());
    }

}
