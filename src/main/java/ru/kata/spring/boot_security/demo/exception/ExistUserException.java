package ru.kata.spring.boot_security.demo.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class ExistUserException extends DataIntegrityViolationException {
    public ExistUserException(String msg) {
        super(msg);
    }
}
