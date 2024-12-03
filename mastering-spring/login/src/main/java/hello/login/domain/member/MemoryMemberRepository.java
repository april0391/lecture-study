package hello.login.domain.member;

import hello.login.web.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
//@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static final Map<Long, Member> store = new ConcurrentHashMap<>();
    private static final AtomicLong sequence = new AtomicLong();

    @Override
    public Member save(Member member) {
        member.setId(sequence.incrementAndGet());
        log.info("save: member={}", member);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByLoginId(String loginId) {
        return findAll().stream()
                .filter(member -> member.getLoginId().equals(loginId))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void clearStore() {
        store.clear();
    }

}
