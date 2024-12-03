package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest  // 스프링 애플리케이션 컨텍스트를 로드하여 통합 테스트 실행
@AutoConfigureMockMvc  // MockMvc 자동 설정
@Transactional  // 테스트 후 데이터베이스 롤백을 보장하기 위해 사용
public class MemberControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;  // MockMvc를 사용하여 컨트롤러 테스트

    @Autowired
    private MemberService memberService;  // 실제 서비스 주입

    @Autowired
    private MemberRepository memberRepository;  // 실제 레포지토리 주입

    @Test
    public void testCreateMember() throws Exception {
        // POST 요청을 모의하여 실제 서비스 및 레포지토리 호출
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/members/new")
                        .param("name", "John Doe"))  // form 파라미터 설정
                .andExpect(status().is3xxRedirection())  // 리디렉션 상태 확인
                .andExpect(redirectedUrl("/"));  // "/"로 리디렉션 확인

        // 실제 데이터베이스에 저장된 멤버를 확인
        Member member = memberRepository.findByName("John Doe").get();
        assert(member.getName().equals("John Doe"));
        Assertions.assertThat(member.getName()).isEqualTo("John Doe");
    }

}
