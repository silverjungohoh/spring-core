package com.practice.springcore.member;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.reactive.context.AnnotationConfigReactiveWebApplicationContext;
import org.springframework.context.ApplicationContext;

import com.practice.springcore.AppConfig;

class MemberServiceTest {

	MemberService memberService;

	@BeforeEach
	void setUp() {
		ApplicationContext applicationContext = new AnnotationConfigReactiveWebApplicationContext(AppConfig.class);
		memberService = applicationContext.getBean("memberService", MemberService.class);
	}

	@DisplayName("회원 가입을 한다.")
	@Test
	void join() {
		// given
		Member newMember = new Member(1L, "kevin", Grade.VIP);

		// when
		memberService.join(newMember);
		Member member = memberService.findMember(1L);

		// then
		assertThat(newMember).isEqualTo(member);
	}
}