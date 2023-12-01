package com.practice.springcore.discount;

import com.practice.springcore.member.Member;

public interface DiscountPolicy {

	int discount(Member member, int price);
}
