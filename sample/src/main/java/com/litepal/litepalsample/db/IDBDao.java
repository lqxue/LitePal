package com.litepal.litepalsample.db;

import com.litepal.crud.LitePalSupport;

import java.util.List;

public interface IDBDao<T extends LitePalSupport> {
    T insert(T t) throws Exception;

    T insertOrUpdate(T t) throws Exception;

    List<T> insert(Iterable<T> objects) throws Exception;

    List<T> insertOrUpdate(Iterable<T> objects) throws Exception;

    void delete(T t) throws Exception;
}
