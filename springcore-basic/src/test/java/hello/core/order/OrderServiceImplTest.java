package hello.core.order;

import hello.core.MyTest;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderServiceImplTest {

    @Test
    void createOrder() {
        MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();
        memoryMemberRepository.save(new Member(1L, "name", Grade.VIP));

//        OrderServiceImpl orderService = new OrderServiceImpl();
//        Order order = orderService.createOrder(1L, "itemA", 10000);
//        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

    @Test
    void test() {
        MyTest myTest = new MyTest();
        System.out.println("myTest.discountPolicy.getClass() = " + myTest.discountPolicy.getClass());
        System.out.println("myTest.memberService.getClass() = " + myTest.memberService.getClass());
        System.out.println("myTest.orderService.getClass() = " + myTest.orderService.getClass());
    }

}