package com.myke.framework.validator;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * hibernate-validator校验工具类
 */
public final class BeanValidateUtil {

    private static Validator validator;

    /**
     * 私有化构造方法
     */
    private BeanValidateUtil() {
    }

    static {
        // 获取验证器工厂
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        // 获取验证器
        validator = validatorFactory.getValidator();
    }


    /**
     * 校验
     *
     * @param bean
     * @param <T>
     * @return
     */
    public static <T> void validate(T bean) {

        // 校验null
        if (Objects.isNull(bean)) {
            isNotEmpty(getNullValidateResultList());
        }
        // 执行验证,使用默认分组校验
        Set<ConstraintViolation<T>> validateSet = validator.validate(bean);
        isNotEmpty(getValidateResultList(validateSet));
    }

    public static <T> void validate(T bean, Class<?>... groups) {
        // 校验null
        if (Objects.isNull(bean)) {
            isNotEmpty(getNullValidateResultList());
        }
        // 执行验证,使用分组校验
        Set<ConstraintViolation<T>> validateSet = validator.validate(bean, groups);
        isNotEmpty(getValidateResultList(validateSet));
    }

    public static <T> void validate(List<T> list) {
        for (T dto : list) {
            // 校验null
            if (Objects.isNull(dto)) {
                isNotEmpty(getNullValidateResultList());
            }
            // 执行验证
            Set<ConstraintViolation<T>> validateSet = validator.validate(dto);
            isNotEmpty(getValidateResultList(validateSet));
        }

    }


    private static void isNotEmpty(List<BeanValidateResult> validateResult) {
        if (CollectionUtils.isNotEmpty(validateResult)) {
            throw new ApiParamException(validateResult.get(0).toString());
        }
    }

    /**
     * 获取校验实体list
     *
     * @param validateSet
     * @param <T>
     * @return
     */
    private static <T> List<BeanValidateResult> getValidateResultList(Set<ConstraintViolation<T>> validateSet) {
        List<BeanValidateResult> validateResultList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(validateSet)) {
            validateResultList = validateSet.stream().map(BeanValidateUtil::convertToBeanValidateResult).collect(Collectors.toList());
        }
        return validateResultList;
    }


    /**
     * 转换类型
     *
     * @param validate
     * @param <T>
     * @return
     */
    private static <T> BeanValidateResult convertToBeanValidateResult(ConstraintViolation<T> validate) {
        BeanValidateResult validateResult = new BeanValidateResult();
        validateResult.setFieldName(validate.getPropertyPath().toString());
        validateResult.setMessage(validate.getMessage());
        return validateResult;
    }


    /**
     * 获取bean为null时的校验结果
     *
     * @return
     */
    private static List<BeanValidateResult> getNullValidateResultList() {
        List<BeanValidateResult> validateResultList = new ArrayList<>();
        BeanValidateResult validateResult = new BeanValidateResult();
        validateResult.setFieldName("validateBean");
        validateResult.setMessage("bean is null");
        validateResultList.add(validateResult);
        return validateResultList;
    }

}