package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
@Getter
public class OrderServiceImpl2 implements OrderService {

    private final MemberRepository memberRepository;
    private final Map<String, DiscountPolicy> discountPolicyMap;

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicyMap.get("rateDiscountPolicy")
                .discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

}
