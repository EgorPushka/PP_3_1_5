package ru.kata.spring.boot_security.demo.exception;

import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Getter
@Scope("prototype")
public class InfoMessage {

    private String info;

    public InfoMessage() {
    }

    public InfoMessage(String info) {
        this.info = info;
    }

}
