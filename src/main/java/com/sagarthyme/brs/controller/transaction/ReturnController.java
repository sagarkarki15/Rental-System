package com.sagarthyme.brs.controller.transaction;

import com.sagarthyme.brs.dto.TransactionDto;
import com.sagarthyme.brs.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("returnbook")
public class ReturnController {

    @Autowired
    private TransactionService transactionService;


    @GetMapping
    public String openReturnPage(Model model){
        model.addAttribute("returnList", transactionService.findAll());
        return "returnbook/returnbookpage";
    }

    @GetMapping("return")
    public String openAddReturnPage( Model model){
        model.addAttribute("returnDto", new TransactionDto());
        return "returnbook/addreturnbook/addreturnbookpage";
    }

    @PostMapping("savereturn")
    public String saveReturn(@Valid @ModelAttribute(name = "returnDto") TransactionDto transactionDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "returnbook/addreturnbook/addreturnbookpage";
        }
        transactionService.save(transactionDto);
        return "redirect:/returnbook";
    }
}
