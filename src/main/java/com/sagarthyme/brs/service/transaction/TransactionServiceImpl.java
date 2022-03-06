package com.sagarthyme.brs.service.transaction;

import com.sagarthyme.brs.dto.TransactionDto;
import com.sagarthyme.brs.model.Book;
import com.sagarthyme.brs.model.Member;
import com.sagarthyme.brs.model.Transaction;
import com.sagarthyme.brs.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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

        LocalDate now=LocalDate.now();
        LocalDate to=now.plusDays(transactionDto.getNumberOfDays());
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fromDate = now.format(formatter);
        String toDate = to.format(formatter);
        Date date1=null;
        Date date2= null;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(fromDate);
            date2=new SimpleDateFormat("yyyy-MM-dd").parse(toDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        transactionDto.setFromDate(date1);
        transactionDto.setToDate(date2);

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
                .member(transaction.getMember())
                .book(transaction.getBook())
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
