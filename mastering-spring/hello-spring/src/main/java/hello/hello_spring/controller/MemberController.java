package hello.hello_spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;

@Controller
public class MemberController {

	private final MemberService memberService;

	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping("/members/new")
	public String createForm() {
		return "members/createMemberForm";
	}
	
	@PostMapping("/members/new")
	public String create(MemberForm form) {
		Member member = new Member();
		member.setName(form.getName());
		memberService.join(member);

		System.out.println(member.getId());
		System.out.println(member.getName());

		return "redirect:/";
	}
	
	@GetMapping("/members")
	public String list(Model model) {
        model.addAttribute("members", memberService.findMembers());
		return "members/memberList";
	}
	
}