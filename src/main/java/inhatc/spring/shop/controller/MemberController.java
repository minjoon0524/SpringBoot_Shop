package inhatc.spring.shop.controller;

import inhatc.spring.shop.dto.MemberFormDto;
import inhatc.spring.shop.entity.Member;
import inhatc.spring.shop.service.MemberService;
<<<<<<< HEAD
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
=======
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
>>>>>>> origin/master
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
<<<<<<< HEAD
@Slf4j
=======
>>>>>>> origin/master
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;


    @GetMapping("/member/new")
    public String memberForm(Model model){
        model.addAttribute("memberFormDto",new MemberFormDto());
        return "member/memberForm";
    }

<<<<<<< HEAD
    @PostMapping(value = "/member/new")
    public String saveMember(@Valid MemberFormDto memberFormDto,
                             BindingResult bindingResult, Model model) {

        log.info("===============> saveMember 정상 호출 : " + memberFormDto);   // 전달 내용 확인!!

        if(bindingResult.hasErrors()) {     // 입력값 검증 결과 에러가 있을 경우
            return "member/memberForm";     // 회원가입 페이지로 이동
        }

        try{
            Member member = Member.createMember(memberFormDto, passwordEncoder);    // 회원가입 정보를 Member 객체로 변환
            memberService.saveMember(member);                                       // 회원가입 정보를 DB에 저장
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "member/memberForm";
        }
        return "redirect:/";
    }

    @GetMapping("/member/login")
    public String loginForm(){
        return "member/memberLoginForm";
    }
=======
//    @PostMapping("/member/new")
//        public String insertMember(MemberFormDto memberFormDto){
//            Member member=Member.createMember(memberFormDto,passwordEncoder);
//        Member m = memberService.saveMember(member);
//
//        return "";
//        }

>>>>>>> origin/master
}
