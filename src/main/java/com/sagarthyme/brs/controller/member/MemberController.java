package com.sagarthyme.brs.controller.member;

import com.sagarthyme.brs.dto.MemberDto;
import com.sagarthyme.brs.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("member")
public class MemberController {

    @Autowired
    public MemberService memberService;

    @GetMapping
    public String openMemberPage(Model model){
        model.addAttribute("listMember", memberService.findAll());
        return ("member/memberpage");
    }

    @GetMapping("addmember")
    public String openAddMemberPage(Model model){
        model.addAttribute("memberDto", new MemberDto());
        return ("member/addmember/addmemberpage");
    }

    @PostMapping("savemember")
    public String saveMember(@Valid @ModelAttribute MemberDto memberDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ("member/addmember/addmemberpage");
        }
        memberService.saveMember(memberDto);
        return ("redirect:/member");
    }

    @GetMapping("updatemember/{id}")
    public String updateMember(@PathVariable("id") Integer id, Model model){
        model.addAttribute("memberDto", memberService.findById(id));
        return ("member/addmember/addmemberpage");
    }

    @GetMapping("deletemember/{id}")
    public String deleteMember(@PathVariable("id") Integer id){
        memberService.deleteById(id);
        return "redirect:/member";
    }
}
