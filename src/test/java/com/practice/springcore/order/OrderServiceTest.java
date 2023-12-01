package com.practice.springcore.order;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.practice.springcore.member.Grade;
import com.practice.springcore.member.Member;
import com.practice.springcore.member.MemberService;
import com.practice.springcore.member.MemberServiceImpl;

class OrderServiceTest {

	MemberService memberService = new MemberServiceImpl();
	OrderService orderService = new OrderServiceImpl();

	@DisplayName("주문을 생성한다.")
	@Test
	void createOrder() {
		// given
		Long memberId = 1L;
		Member member = new Member(memberId, "kevin", Grade.VIP);
		memberService.join(member);

		// when
		Order order = orderService.createOrder(1L, "item", 10000);

		// then
		assertThat(order.getDiscountPrice()).isEqualTo(1000);
	}
}