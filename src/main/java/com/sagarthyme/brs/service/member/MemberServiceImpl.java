package com.sagarthyme.brs.service.member;

import com.sagarthyme.brs.dto.MemberDto;
import com.sagarthyme.brs.model.Member;
import com.sagarthyme.brs.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    public MemberRepository memberRepository;

    @Override
    public List<MemberDto> findAll() {
        List<Member> memberList = memberRepository.findAll();
        return memberList.stream().map(member -> MemberDto.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .address(member.getAddress())
                .mobileNumber(member.getMobileNumber()).build()).collect(Collectors.toList());
    }

    @Override
    public MemberDto saveMember(MemberDto memberDto) {
        Member entity = Member.builder()
                .id(memberDto.getId())
                .name(memberDto.getName())
                .email(memberDto.getEmail())
                .address(memberDto.getAddress())
                .mobileNumber(memberDto.getMobileNumber()).build();
        entity = memberRepository.save(entity);
        return MemberDto.builder().id(entity.getId()).build();
    }

    @Override
    public void deleteById(Integer integer) {
        memberRepository.deleteById(integer);
    }

    @Override
    public MemberDto findById(Integer integer) {
        Optional<Member> optionalMember = memberRepository.findById(integer);
        if (optionalMember.isPresent()){
            Member member = optionalMember.get();

            return MemberDto.builder()
                    .id(member.getId())
                    .name(member.getName())
                    .email(member.getEmail())
                    .address(member.getAddress())
                    .mobileNumber(member.getMobileNumber()).build();
        }
        return null;
    }
}
