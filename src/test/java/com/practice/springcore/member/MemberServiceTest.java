package com.practice.springcore.member;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

	MemberService memberService = new MemberServiceImpl();

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