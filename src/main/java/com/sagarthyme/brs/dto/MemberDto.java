package com.sagarthyme.brs.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    private Integer id;

    @NotEmpty(message = "Name must be entered")
    private String name;

    @NotEmpty(message = "Email must be entered")
    @Email(message = "Please, enter valid email")
    private String email;

    @NotEmpty(message = "Address must be entered")
    private String address;

    @NotEmpty(message = "Mobile Number must be entered")
    @Size(max = 10, message = "Please enter the valid number length.")
    private String mobileNumber;
}
