package com.sagarthyme.brs.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {

    private Integer id;

    @NotEmpty(message = "Please, enter a category name.")
    private String name;

    @NotEmpty(message = "Write a short description!!")
    private String description;
}
