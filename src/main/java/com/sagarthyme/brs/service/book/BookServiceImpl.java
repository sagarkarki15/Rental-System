package com.sagarthyme.brs.service.book;

import com.sagarthyme.brs.component.FileStorageComponent;
import com.sagarthyme.brs.dto.BookDto;
import com.sagarthyme.brs.dto.MemberDto;
import com.sagarthyme.brs.dto.ResponseDto;
import com.sagarthyme.brs.model.Author;
import com.sagarthyme.brs.model.Book;
import com.sagarthyme.brs.model.Category;
import com.sagarthyme.brs.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BookServiceImpl implements BookService{

    @Autowired
    public BookRepository bookRepository;

    @Autowired
    public FileStorageComponent fileStorageComponent;

    @Override
    public List<BookDto> findAll() {
        List<Book> bookList = bookRepository.findAll();

        return bookList.stream().map(book -> BookDto.builder()
                .id(book.getId())
                .name(book.getName())
                .numberOfPages(book.getNumberOfPages())
                .isbn(book.getIsbn())
                .rating(book.getRating())
                .stockCount(book.getStockCount())
                .publishedDate(book.getPublishedDate()).build()).collect(Collectors.toList());
    }

    @Override
    public BookDto save(BookDto bookDto) throws IOException {

        List<Author> authorList = new ArrayList<>();
        bookDto.getAuthorDtoList().forEach(item -> {
            Author author = new Author();
            author.setId(item);
            authorList.add(author);
        });

        Category category = new Category();
        category.setId(bookDto.getCategoryDto());

        ResponseDto responseDto = fileStorageComponent.storeFile(bookDto.getMultipartFile());
        if (responseDto.isStatus()) {
            Book entity = Book.builder()
                    .id(bookDto.getId())
                    .name(bookDto.getName())
                    .numberOfPages(bookDto.getNumberOfPages())
                    .isbn(bookDto.getIsbn())
                    .rating(bookDto.getRating())
                    .stockCount(bookDto.getStockCount())
                    .authorList(authorList)
                    .category(category)
                    .publishedDate(bookDto.getPublishedDate())
                    .filePath(responseDto.getMessage()).build();
            entity = bookRepository.save(entity);
            return BookDto.builder().id(entity.getId()).build();
        }
        else {
            log.error(responseDto.getMessage());
            return null;
        }
    }

    @Override
    public BookDto findById(Integer integer) throws IOException {
        Optional<Book> optionalBook = bookRepository.findById(integer);
        if (optionalBook.isPresent()){
            Book book = optionalBook.get();
            return BookDto.builder()
                    .id(book.getId())
                    .name(book.getName())
                    .numberOfPages(book.getNumberOfPages())
                    .isbn(book.getIsbn())
                    .rating(book.getRating())
                    .stockCount(book.getStockCount())
                    .publishedDate(book.getPublishedDate())
                    .filePath(fileStorageComponent.returnFileAsBase64(book.getFilePath())).build();
        }
        return null;
    }

    @Override
    public void deleteById(Integer integer) {
        bookRepository.deleteById(integer);
    }
}
