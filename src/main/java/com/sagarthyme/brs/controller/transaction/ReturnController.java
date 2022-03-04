package com.sagarthyme.brs.controller.transaction;

import com.sagarthyme.brs.dto.TransactionDto;
import com.sagarthyme.brs.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
