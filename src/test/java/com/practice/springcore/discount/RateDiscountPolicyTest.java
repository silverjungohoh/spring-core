package com.practice.springcore.discount;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.practice.springcore.member.Grade;
import com.practice.springcore.member.Member;

class RateDiscountPolicyTest {

	RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

	@DisplayName("VIP 경우 10% 할인이 적용되어야 한다.")
	@Test
	void discountVip() {
		// given
		Member member = new Member(1L, "kevin", Grade.VIP);
		int price = 10000;
		// when
		int discountPrice = discountPolicy.discount(member, price);
		// then
		assertThat(discountPrice).isEqualTo(1000);
	}

	@DisplayName("BASIC 경우 할인이 적용될 수 없다.")
	@Test
	void discountBasic() {
		// given
		Member member = new Member(1L, "kevin", Grade.BASIC);
		int price = 10000;
		// when
		int discountPrice = discountPolicy.discount(member, price);
		// then
		assertThat(discountPrice).isEqualTo(0);
	}
}