package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MemoryMemberRepository implements MemberRepository {

	private static final Map<Long, Member> store = new ConcurrentHashMap<>();
	private static final AtomicLong sequence = new AtomicLong(0L);

	@Override
	public Member save(Member member) {
		long id = sequence.incrementAndGet();
		member.setId(id);
		store.put(member.getId(), member);
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		Member member = store.get(id);
		return Optional.ofNullable(member);
	}

	@Override
	public Optional<Member> findByName(String name) {
		/*for (Member value : store.values()) {
			if (name.equals(value.getName())) {
				return Optional.of(value);
			}
		}
		return Optional.empty();*/
		return store.values().stream()
				.filter(member -> member.getName().equals(name))
				.findFirst();
	}

	@Override
	public List<Member> findAll() {
		return new ArrayList<>(store.values());
//		return store.values().stream().toList(); // 불변 리스트
	}

	public void clearStore() {
		store.clear();
	}

}
