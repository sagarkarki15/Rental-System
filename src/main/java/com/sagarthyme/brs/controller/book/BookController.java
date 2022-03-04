package com.sagarthyme.brs.controller.book;

import com.sagarthyme.brs.dto.BookDto;
import com.sagarthyme.brs.service.author.AuthorService;
import com.sagarthyme.brs.service.book.BookService;
import com.sagarthyme.brs.service.category.CategoryService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("book")
public class BookController {

    @Autowired
    public CategoryService categoryService;

    @Autowired
    public BookService bookService;

    @Autowired
    public AuthorService authorService;

    @GetMapping
    public String openBookPage(Model model){
        model.addAttribute("listBook", bookService.findAll());
        return "book/bookpage";
    }

    @GetMapping("addbooks")
    public String openAddBookPage(Model model){
        model.addAttribute("listAuthor", authorService.findAll());
        model.addAttribute("listCategory", categoryService.findAll());
        model.addAttribute("bookDto", new BookDto());
        return ("book/addbooks/addbookpage");
    }

    @PostMapping("addbook")
    public String saveBook(@Valid @ModelAttribute BookDto bookDto, BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()){
            return ("book/addbooks/addbookpage");
        }

//        bookDto =
        bookService.save(bookDto);
        /*if (bookDto != null){
            redirectAttributes.addFlashAttribute("message", "Book added successfully");
        }
        else {
            redirectAttributes.addFlashAttribute("message", "Book addition failed");
        }*/
        return "redirect:/book";
    }

    @GetMapping("view/{id}")
    public String viewBook(@PathVariable("id") Integer id,Model model) throws IOException {
        model.addAttribute("bookDto", bookService.findById(id));
        return "book/view";
    }

    @GetMapping("updatebook/{id}")
    public String updateBook(@PathVariable("id") Integer id,Model model) throws IOException {
        model.addAttribute("listAuthor", authorService.findAll());
        model.addAttribute("listCategory", categoryService.findAll());
        model.addAttribute("bookDto",bookService.findById(id));
        return "book/addbooks/addbookpage";
    }

    @GetMapping("deletebook/{id}")
    public String deleteBook(@PathVariable("id") Integer id){
        bookService.deleteById(id);
        return ("redirect:/book");
    }
}
