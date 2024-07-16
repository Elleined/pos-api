package com.elleined.pos_api.service;

public interface CustomService<T> {
    T getById(int id);
}
