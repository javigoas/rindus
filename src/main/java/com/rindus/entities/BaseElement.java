package com.rindus.entities;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class BaseElement {
    @Size(min = 1, max = 3, message = "Respect the length between 1 and 3 digits")
    private Integer id;
    @Size(max = 200, message = "body can not be longer than 200 character")
    private String body;
}
