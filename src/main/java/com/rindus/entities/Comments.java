package com.rindus.entities;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class Comments extends BaseElement{
    private Integer postId;
    @Size(max = 100, message = "name can not be longer than 100")
    private String name;
    @Email(message = "Email should be valid")
    private String email;
}
