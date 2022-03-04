package com.sagarthyme.brs.dto;

import lombok.*;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorDto {

    private Integer id;

    @NotEmpty(message = "Name must be entered")
    private String name;

    @NotEmpty(message = "This field must be filled.")
    @Email(message = "email should be valid.")
    private String email;

    @NotEmpty(message = "Number must be entered.")
    @Size(max = 10, message = "Please enter the valid number length.")
    private String mobileNumber;

    private String message;

    private String subject;
}
