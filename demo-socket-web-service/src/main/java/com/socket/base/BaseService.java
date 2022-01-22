package com.socket.base;

import org.springframework.data.domain.Page;

import java.util.List;

public interface BaseService<T> {
    T add(T t);
    void delete(Long id);
    void deleteBatch(Long[] listId);
    T update(T t);
    List<T> updateBatch(List<T> listT);
    Page<T> getPage(String search, int page, int size);
    T get(Long id);
}
