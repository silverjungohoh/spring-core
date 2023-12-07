package com.practice.springcore.discount;

import org.springframework.stereotype.Component;

import com.practice.springcore.annotation.MainDiscountPolicy;
import com.practice.springcore.member.Grade;
import com.practice.springcore.member.Member;

@Component
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {

	private int discountPercent = 10;

	@Override
	public int discount(Member member, int price) {
		if (member.getGrade() == Grade.VIP) {
			return price * discountPercent / 100;
		} else {
			return 0;
		}
	}
}
