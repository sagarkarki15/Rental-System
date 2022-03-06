package com.sagarthyme.brs.controller.transaction;

import com.sagarthyme.brs.dto.TransactionDto;
import com.sagarthyme.brs.service.book.BookService;
import com.sagarthyme.brs.service.member.MemberService;
import com.sagarthyme.brs.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("rentbook")
public class RentController {

    @Autowired
    public BookService bookService;

    @Autowired
    public MemberService memberService;

    @Autowired
    public TransactionService transactionService;

    @GetMapping
    public String openMainPage(Model model){
        model.addAttribute("rentList", transactionService.findAll());
//        model.addAttribute("memberList", memberService.findAll());
        return "rentbook/rentbookpage";
    }

    @GetMapping("addrentbook")
    public String openAddRentBook(Model model){
        model.addAttribute("listBook", bookService.findAll());
        model.addAttribute("listMember", memberService.findAll());
        model.addAttribute("rentDto", new TransactionDto());
        return "rentbook/addrentbook/addrentbookpage";
    }

    @PostMapping("saverent")
    public String saveRent(@Valid @ModelAttribute(name = "rentDto") TransactionDto transactionDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "rentbook/addrentbook/addrentbookpage";
        }
        transactionService.save(transactionDto);
        return "redirect:/rentbook";
    }

    @GetMapping("updaterent/{id}")
    public String updateRent(@PathVariable("id")Integer id, Model model){
        model.addAttribute("rentDto", transactionService.findById(id));
        model.addAttribute("listBook", bookService.findAll());
        model.addAttribute("listMember", memberService.findAll());
        return "rentbook/addrentbook/addrentbookpage";
    }

    @GetMapping("deleterent/{id}")
    public String deleteRent(@PathVariable("id")Integer id){
        transactionService.deleteById(id);
        return "redirect:/rentbook";
    }
}
