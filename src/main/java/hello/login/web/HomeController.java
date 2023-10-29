package hello.login.web;

import hello.login.domain.SessionConst;
import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import hello.login.web.argumentresolver.Login;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

    /*@GetMapping("/")
    public String homeLogin(
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER,required = false) Member loginMember,
            Model model){
        //세션에 회원데이터가없으면 home
        if (loginMember == null) {
            return "home";
        }
        model.addAttribute("member", loginMember);
        return "loginhome";
    }*/

    @GetMapping("/")
    public String homeLoginArgumentResolver(@Login Member loginMember, Model model){
        //세션에 회원데이터가없으면 home
        if (loginMember == null) {
            return "home";
        }
        model.addAttribute("member", loginMember);
        return "loginhome";
    }

}