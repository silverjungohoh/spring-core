package com.practice.springcore.member;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.practice.springcore.AppConfig;

class MemberServiceTest {

	MemberService memberService;

	@BeforeEach
	void setUp() {
		AppConfig appConfig = new AppConfig();
		memberService = appConfig.memberService();
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