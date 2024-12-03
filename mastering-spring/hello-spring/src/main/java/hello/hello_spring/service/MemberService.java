package hello.hello_spring.service;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;

@Transactional
public class MemberService {

	private final MemberRepository memberRepository;

	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public Long join(Member member) {
		validateDuplicateMember(member); // 중복 회원 검증
		memberRepository.save(member);
		return member.getId();
	}

	private void validateDuplicateMember(Member member) {
		memberRepository.findByName(member.getName())
			.ifPresent(m -> {
				throw new IllegalStateException("이미 존재하는 회원입니다.");
			});
	}
	
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}

	public Optional<Member> findById(Long memberId) {
		return memberRepository.findById(memberId);
	}
	
	
	
	
	
}
