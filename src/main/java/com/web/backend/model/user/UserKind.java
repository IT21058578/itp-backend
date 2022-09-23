package com.web.backend.model.user;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import org.checkerframework.common.value.qual.EnumVal;

public abstract class UserKind {
    protected UserType type;
}
