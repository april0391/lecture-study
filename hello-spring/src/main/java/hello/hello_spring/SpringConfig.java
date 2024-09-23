package hello.hello_spring;

import hello.hello_spring.repository.JdbcMemberRepository;
import hello.hello_spring.repository.JdbcTemplateMemberRepository;
import hello.hello_spring.repository.JpaMemberRepository;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.service.MemberService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class SpringConfig {

	/*private final DataSource dataSource;
	private final EntityManager entityManager;

	@Autowired
	public SpringConfig(DataSource dataSource, EntityManager entityManager) {
		this.dataSource = dataSource;
		this.entityManager = entityManager;
	}*/

	private final MemberRepository memberRepository;

	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository);
	}

	/*@Bean
	public MemberRepository memberRepository() {
//		return new MemoryMemberRepository();
//		return new JdbcMemberRepository(dataSource);
//		return new JdbcTemplateMemberRepository(dataSource);
		return new JpaMemberRepository(entityManager);
	}*/

}
