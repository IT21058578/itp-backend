package com.web.backend.model.user;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import org.checkerframework.common.value.qual.EnumVal;

import lombok.Data;

@Data
public abstract class UserKind {
    protected UserType type;
}
