package hello.hello_spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.hello_spring.aop.TimeTraceAop;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.service.MemberService;

@Configuration
public class SpringConfig {

//	private DataSource dataSource;
//	
//	@Autowired
//	public SpringConfig(DataSource datasource) {
//		this.dataSource = datasource;
//	}
	
//	private EntityManager em;
//	
//	@Autowired
//	public SpringConfig(EntityManager em) {
//		super();
//		this.em = em;
//	}
	
	private final MemberRepository memberRepository;

	public SpringConfig(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository);
	}
	
//	@Bean
//	public TimeTraceAop TimeTraceAop() {
//		return new TimeTraceAop();
//	}
	
//	@Bean
//	public MemberRepository memberRepository() {
////		return new MemoryMemberRepository();
////		return new JdbcMemberRepository(dataSource);
////		return new JdbcTemplateMemberRepository(dataSource);
//		return new JpaMemberRepository(em);
//	}
}
