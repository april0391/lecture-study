package hello.core.order;

import hello.core.MyTest;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void createOrder() {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        DiscountPolicy discountPolicy = new RateDiscountPolicy();
        memberRepository.save(new Member(1L, "itemA", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, discountPolicy);
        Order order = orderService.createOrder(1L, "itemA", 10000);
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

    @Test
    void test() {
        MyTest myTest = new MyTest();
        System.out.println("myTest.discountPolicy.getClass() = " + myTest.discountPolicy.getClass());
        System.out.println("myTest.memberService.getClass() = " + myTest.memberService.getClass());
        System.out.println("myTest.orderService.getClass() = " + myTest.orderService.getClass());
    }

}