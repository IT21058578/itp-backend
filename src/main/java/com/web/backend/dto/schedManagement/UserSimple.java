package com.web.backend.dto.schedManagement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class UserSimple {
    private String id;
    private String name;
    private String email;
    private String type;
}
