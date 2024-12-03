package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelAndView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberListController implements ControllerV3 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelAndView process(Map<String, String> paramMap) throws ServletException, IOException {
        List<Member> members = memberRepository.findAll();
        HashMap<String, Object> model = new HashMap<>();
        model.put("members", members);
        return new ModelAndView("members", model);
    }
}
