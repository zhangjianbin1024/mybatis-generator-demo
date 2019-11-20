package com.myke.service;

/**
 * 通用 service
 *
 * @param <T>
 */
public interface CrudService<T> extends
        InsertService<T>,
        UpdateService<T>,
        DeleteService<T>,
        SelectService<T> {
}
