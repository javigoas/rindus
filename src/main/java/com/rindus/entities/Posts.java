package com.rindus.entities;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class Posts extends BaseElement{
    private Integer userId;
    @Size(max = 100, message = "title should not be longer than 100 characters")
    private String title;
}
