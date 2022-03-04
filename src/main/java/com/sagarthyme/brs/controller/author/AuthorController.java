package com.sagarthyme.brs.controller.author;

import com.sagarthyme.brs.dto.AuthorDto;
import com.sagarthyme.brs.model.Author;
import com.sagarthyme.brs.service.author.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("author")
public class AuthorController {

    @Autowired
    public AuthorService authorService;

    //display author list
    @GetMapping
    public String openAuthorPage(Model model){
        model.addAttribute("listAuthor", authorService.findAll());
        return "author/authorpage";
    }

    @GetMapping("/addauthor")
    public String openAddAuthorPage(Model model){
        model.addAttribute("authorDto",new AuthorDto());
        return "author/addauthor/addauthorpage";
    }

    @PostMapping("saveauthor")
    public String saveAuthor(@Valid @ModelAttribute AuthorDto authorDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "author/addauthor/addauthorpage";
        }
        authorService.create(authorDto);
        authorService.sendMail(authorDto);
        return "redirect:/author";
    }

    @GetMapping("updateauthor/{id}")
    public String updateAuthor(@PathVariable("id") Integer id, Model model){
        model.addAttribute("authorDto", authorService.findById(id));
        return ("author/addauthor/addauthorpage");
    }

    @GetMapping("deleteauthor/{id}")
    public String deleteAuthor(@PathVariable("id") Integer id){
        authorService.deleteById(id);
        return ("redirect:/author");
    }
}
