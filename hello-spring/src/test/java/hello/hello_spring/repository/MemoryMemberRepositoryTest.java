package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {

	MemoryMemberRepository repository = new MemoryMemberRepository();
		
		@AfterEach
		public void afterEach() {
			repository.clearStore();
		}

		@Test
		public void save() {
			// given
			Member newMember = new Member();
			newMember.setName("hello");

			// when
			repository.save(newMember);
			Member savedMember = repository.findById(newMember.getId()).get();

			// then
			assertThat(newMember).isEqualTo(savedMember);
		}
		
		@Test
		public void findByName() {
			Member member1 = new Member();
			member1.setName("member1");
			repository.save(member1);

			Member member2 = new Member();
			member2.setName("member2");
			repository.save(member2);

			Member findMember = repository.findByName("member1")
					.orElseThrow(IllegalStateException::new);

			assertThat(member1).isEqualTo(findMember);
		}

		@Test
		public void findAll() {
			Member member1 = new Member();
			member1.setName("member1");
			repository.save(member1);

			Member member2 = new Member();
			member2.setName("member2");
			repository.save(member2);

			List<Member> members = repository.findAll();
			assertThat(members).contains(member1, member2);
			assertThat(members.size()).isEqualTo(2);
		}
}
