package com.sagarthyme.brs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String code;

    private String fromDate;

    private String toDate;

    private String rentStatus;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id",foreignKey = @ForeignKey(name = "fk_transaction_book"))
    public Book book;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id",foreignKey = @ForeignKey(name = "fk_transaction_member"))
    public Member member;

}
