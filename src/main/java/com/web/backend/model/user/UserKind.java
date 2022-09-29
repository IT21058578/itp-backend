package com.web.backend.model.user;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import lombok.AllArgsConstructor;
import org.checkerframework.common.value.qual.EnumVal;

import lombok.Data;

@Data @AllArgsConstructor
public abstract class UserKind {
    protected UserType type;
}
