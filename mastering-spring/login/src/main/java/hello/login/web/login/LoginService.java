package hello.login.web.login;

import hello.login.domain.member.MemberRepository;
import hello.login.web.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(String loginId, String password) {
        Optional<Member> byLoginId = memberRepository.findByLoginId(loginId);
        return byLoginId.filter(m -> m.getPassword().equals(password))
                .orElseThrow(() -> new IllegalArgumentException("loginFail"));
    }

    public Member findById(Long id) {
        return memberRepository.findById(id).orElseThrow();
    }

}
