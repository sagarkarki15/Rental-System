package com.sagarthyme.brs.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
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

    //photo
    private String filePath;

    private MultipartFile multipartFile;
}
