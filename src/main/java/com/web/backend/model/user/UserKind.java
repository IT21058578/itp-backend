package com.web.backend.model.user;

import lombok.Data;

@Data
public abstract class UserKind {
    protected UserType type;
}
