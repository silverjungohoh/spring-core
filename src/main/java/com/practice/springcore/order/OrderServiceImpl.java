package com.practice.springcore.order;

import com.practice.springcore.discount.DiscountPolicy;
import com.practice.springcore.discount.RateDiscountPolicy;
import com.practice.springcore.member.Member;
import com.practice.springcore.member.MemberRepository;
import com.practice.springcore.member.MemoryMemberRepository;

/**
 * DiscountPolicy 인터페이스에 의존, but 구현 클래스(FixDiscountPolicy, RateDiscountPolicy)에 의존하고 있음 (OCP 위반)
 */

public class OrderServiceImpl implements OrderService {

	private final MemberRepository memberRepository = new MemoryMemberRepository();
	// private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
	private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		Member member = memberRepository.findById(memberId);
		int discountPrice = discountPolicy.discount(member, itemPrice);
		return new Order(memberId, itemName, itemPrice, discountPrice);
	}
}
