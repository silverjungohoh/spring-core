package com.practice.springcore.xml;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.practice.springcore.member.MemberService;

public class XMLAppContextTest {

	@DisplayName("XML 기반의 스프링 빈 설정을 사용한다.")
	@Test
	void xmlAppContext() {
		ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
		MemberService memberService = ac.getBean("memberService", MemberService.class);
		assertThat(memberService).isInstanceOf(MemberService.class);
	}
}
