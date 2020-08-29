package com.example.demo.controller;

import com.example.demo.domain.Address;
import com.example.demo.domain.Member;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping(value="/member")
    public String getMemberForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "memberForm";
    }

    @PostMapping(value="/member")
    public String createMember(@Valid MemberForm memberForm, BindingResult result){
        /*
        form 제출 데이터는 엔티티 보다는 Dto 클래스를 만둘어서 받는게 좋다.
        Dto 클래스에서 유효성 검사, 요구사항과 관련된 부분을 구현하고 엔티티 클래스는 jpa 관련 코드만 작성한다.
        반대로, 서버에서 화면으로 데이터를 내려줄때에도 Dto 객체에 필요한 데이터만 담아서 내려주는게 좋다.

        ※참고: api 호출시에는 절대 엔티티 객체를 그대로 내려주면 안된다.
        -> 엔티티 칼럼 추가시 api 스펙이 변함(불안정), api 호출과 관련 없는 정보까지 노출됨.
        */

        if(result.hasErrors()){
            return "memberForm";
        }

        Address address = new Address(memberForm.getCity(), memberForm.getStreet(),
                memberForm.getZipcode());
        Member member = new Member();
        member.setName(memberForm.getName());
        member.setAddress(address);
        memberService.join(member);
        return "redirect:/";

    }
}
