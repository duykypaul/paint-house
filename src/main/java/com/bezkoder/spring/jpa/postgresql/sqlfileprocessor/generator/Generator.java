package com.bezkoder.spring.jpa.postgresql.sqlfileprocessor.generator;

public interface Generator<T> {
    T generate();
}
