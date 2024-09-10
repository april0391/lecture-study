package hello.aop.member;

import hello.aop.member.annotation.MethodAop;

public class MemberServiceImpl implements MemberService {
    @Override
    @MethodAop
    public String hello(String param) {
        return "ok";
    }

    public String internal(String param) {
        return "ok";
    }
}
