package com.sagarthyme.brs.service.member;

import com.sagarthyme.brs.dto.MemberDto;
import com.sagarthyme.brs.model.Member;

import java.util.List;

public interface MemberService {

    List<MemberDto> findAll();
    MemberDto saveMember(MemberDto memberDto);
    void deleteById(Integer integer);
    MemberDto findById(Integer integer);
}
