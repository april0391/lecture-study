package hello.login.web.member;

import hello.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("member", new MemberFormDto());
        return "members/addMemberForm";
    }

    @PostMapping("/add")
    public String save(@Validated @ModelAttribute("member") MemberFormDto memberFormDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("bindingResult = " + bindingResult);
            return "members/addMemberForm";
        }
        Member member = new Member(memberFormDto.getLoginId(), memberFormDto.getName(), memberFormDto.getPassword());
        memberRepository.save(member);
        return "redirect:/";
    }



}
