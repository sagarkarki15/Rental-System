package com.sagarthyme.brs.service.book;


import com.sagarthyme.brs.dto.BookDto;

import java.io.IOException;
import java.util.List;

public interface BookService {

    List<BookDto> findAll();

    BookDto save(BookDto bookDto) throws IOException;

    BookDto findById(Integer integer) throws IOException;

    void deleteById(Integer integer);
}
