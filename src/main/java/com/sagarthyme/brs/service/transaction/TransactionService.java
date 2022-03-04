package com.sagarthyme.brs.service.transaction;

import com.sagarthyme.brs.dto.TransactionDto;

import java.util.List;

public interface TransactionService {

    TransactionDto save(TransactionDto transactionDto);
    List<TransactionDto> findAll();
    TransactionDto findById(Integer id);
    void deleteById(Integer id);
}
