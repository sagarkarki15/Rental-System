package com.sagarthyme.brs.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

    private Integer id;

    @NotEmpty(message = "Please, enter the code.")
    private String code;

    private String fromDate;

    private String toDate;

    private String rentStatus;

    private Integer bookDto;

    private Integer memberDto;
}
