package com.company.tree.Entity.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class BlogRequest{

    @NotBlank(message = "this is not allowed as it is null or empty")
    private String title;
    @NotNull(message = "this is not allowed as it is null")
    private String content;

    private Date createdAt;


    private Date updatedAt;
}
