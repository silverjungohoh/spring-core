package com.practice.springcore.singleton;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.practice.springcore.AppConfig;
import com.practice.springcore.member.MemberRepository;
import com.practice.springcore.member.MemberServiceImpl;
import com.practice.springcore.order.OrderServiceImpl;

public class ConfigurationSingletonTest {

	@DisplayName("memberRepository 인스턴스는 모두 같은 인스턴스가 공유되어 사용된다.")
	@Test
	void configurationTest() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
		OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
		MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

		// 모두 같은 인스턴스를 참조하고 있음
		System.out.println("memberService >> memberRepository = " + memberService.getMemberRepository());
		System.out.println("orderService >> memberRepository = " + orderService.getMemberRepository());
		System.out.println("memberRepository = " + memberRepository);

		assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
		assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
	}

	@DisplayName("스프링은 바이트코드를 조작하는 라이브러리를 사용한다")
	@Test
	void configurationDeep() {
		// 파라미터로 넘긴 값 또한 스프링 빈으로 등록됨 (AppConfig 또한 스프링 빈이 됨)
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

		AppConfig bean = ac.getBean(AppConfig.class);

		System.out.println("bean = " + bean.getClass()); // 순수한 클래스가 아닌 AppConfig$$SpringCGLIB$$0
	}
}

// 스프링은 CGLIB 바이트코드 조작 라이브러리를 사용하여
// AppConfig 상속 받은 임의의 다른 클래스를 만들고 스프링 빈으로 등록
// 그 임의의 다른 클래스가 싱글톤이 보장되도록 해줌
// @Bean 붙은 메서드마다 이미 스프링 빈이 존재하면 존재하는 빈을 반환
// 없으면 생성해서 스프링 빈으로 등록하고 반환