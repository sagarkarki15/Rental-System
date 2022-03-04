package com.sagarthyme.brs.service.author;

import com.sagarthyme.brs.dto.AuthorDto;
import com.sagarthyme.brs.model.Author;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AuthorService {
    List<AuthorDto> findAll();

    AuthorDto create(AuthorDto authorDto);

    AuthorDto findById(Integer integer);

    void deleteById(Integer integer);

    void sendMail(AuthorDto authorDto);
}
