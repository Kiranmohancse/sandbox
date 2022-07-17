package com.apple.sandbox.sandbox.service.manager;


import org.springframework.validation.annotation.Validated;

import com.apple.sandbox.sandbox.model.JavaBeans;

import javax.validation.constraints.NotBlank;

@Validated
public interface ShortnerHandler {
    public String getURL(@NotBlank String key);
    public JavaBeans vanityURL(@NotBlank String url);
}
