package hello.login.domain.member;


import hello.login.web.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;

    @Override
    @Transactional
    public Member save(Member member) {
        System.out.println("JpaMemberRepository.save");
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByLoginId(String loginId) {
        String jpql = "SELECT m FROM Member m WHERE m.loginId = :loginId";
        Member member = em.createQuery(jpql, Member.class)
                .setParameter("loginId", loginId)
                .getResultStream()
                .findFirst()
                .orElse(null);
        return Optional.ofNullable(member);
    }

    @Override
    public List<Member> findAll() {
        String jpql = "SELECT m FROM Member m";
        return em.createQuery(jpql, Member.class)
                .getResultList();
    }
}
