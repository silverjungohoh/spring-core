package com.practice.springcore.scan;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.practice.springcore.AutoAppConfig;
import com.practice.springcore.member.MemberService;

public class AutoAppConfigTest {

	@Test
	void basicScan() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

		MemberService memberService = ac.getBean(MemberService.class);
		assertThat(memberService).isInstanceOf(MemberService.class);
	}
}
