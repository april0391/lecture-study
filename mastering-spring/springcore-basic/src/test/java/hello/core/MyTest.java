package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.order.Order;
import hello.core.order.OrderService;

public class MyTest {

    AppConfig appConfig = new AppConfig();

    public MemberService memberService = appConfig.memberService();
    public OrderService orderService = appConfig.orderService();
    public DiscountPolicy discountPolicy = appConfig.discountPolicy();

    public void test() {
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
    }

}
