package com.sagarthyme.brs.service.transaction;

import com.sagarthyme.brs.dto.TransactionDto;
import com.sagarthyme.brs.model.Book;
import com.sagarthyme.brs.model.Member;
import com.sagarthyme.brs.model.Transaction;
import com.sagarthyme.brs.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    public TransactionRepository transactionRepository;

    @Override
    public TransactionDto save(TransactionDto transactionDto) {
        Book book = new Book();
        book.setId(transactionDto.getBookDto());

        Member member = new Member();
        member.setId(transactionDto.getMemberDto());

        Transaction entity = Transaction.builder()
                .id(transactionDto.getId())
                .code(transactionDto.getCode())
                .fromDate(transactionDto.getFromDate())
                .toDate(transactionDto.getToDate())
                .rentStatus(transactionDto.getRentStatus())
                .book(book)
                .member(member)
                .build();
        entity = transactionRepository.save(entity);

        return TransactionDto.builder().id(entity.getId()).build();
    }

    @Override
    public List<TransactionDto> findAll() {
        List<Transaction> transactionList = transactionRepository.findAll();
        return transactionList.stream().map(transaction -> TransactionDto.builder()
                .id(transaction.getId())
                .code(transaction.getCode())
                .fromDate(transaction.getFromDate())
                .toDate(transaction.getToDate())
                .rentStatus(transaction.getRentStatus())
                .build()).collect(Collectors.toList());

    }

    @Override
    public TransactionDto findById(Integer id) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);
        if (optionalTransaction.isPresent()){
            Transaction transaction = optionalTransaction.get();
            return TransactionDto.builder()
                    .id(transaction.getId())
                    .code(transaction.getCode())
                    .fromDate(transaction.getFromDate())
                    .toDate(transaction.getToDate())
                    .rentStatus(transaction.getRentStatus())
                    .build();
        }
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        transactionRepository.deleteById(id);
    }
}
