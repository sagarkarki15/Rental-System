package com.sagarthyme.brs.dto;

import com.sagarthyme.brs.model.Book;
import com.sagarthyme.brs.model.Member;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

    private Integer id;

    @NotEmpty(message = "Please, enter the code.")
    private String code;

    private Date fromDate;

    private Date toDate;

    private Integer numberOfDays;

    private String rentStatus;

    private Integer bookDto;

    private Integer memberDto;

    private MemberDto member;

    private BookDto book;

    public static class TransactionDtoBuilder{
        public TransactionDtoBuilder member(Member member){
            this.member = MemberDto.builder()
                    .id(member.getId())
                    .address(member.getAddress())
                    .name(member.getName())
                    .email(member.getEmail())
                    .mobileNumber(member.getMobileNumber())
                    .build();
            //returns transactiondtobuilder object
            return this;
        }

        public TransactionDtoBuilder book(Book book){
            this.book = BookDto.builder()
                    .id(book.getId())
                    .name(book.getName())
                    .isbn(book.getIsbn())
                    .build();
            return this;
        }
    }

}
