package com.sagarthyme.brs.dto;

import com.sagarthyme.brs.model.Author;
import com.sagarthyme.brs.model.Book;
import com.sagarthyme.brs.model.Category;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private Integer id;

    @NotEmpty(message = "Please fill this.")
    private String name;

    private String numberOfPages;

    private String isbn;

    private String rating;

    private String stockCount;

    private String publishedDate;

    private Integer categoryDto;

    private List<Integer> authorDtoList;

    private CategoryDto category;
    private List<AuthorDto> author;

    //photo
    private String filePath;

    private MultipartFile multipartFile;

    public static class BookDtoBuilder{
        public BookDtoBuilder category(Category category){
            this.category = CategoryDto.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .build();
            return this;
        }

        /*public BookDtoBuilder author(List<Author> authors){


            return this;
        }*/
    }
}
