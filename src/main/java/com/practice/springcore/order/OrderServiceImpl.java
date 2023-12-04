package com.practice.springcore.order;

import com.practice.springcore.discount.DiscountPolicy;
import com.practice.springcore.member.Member;
import com.practice.springcore.member.MemberRepository;

/**
 * DiscountPolicy 인터페이스에 의존, but 구현 클래스(FixDiscountPolicy, RateDiscountPolicy)에 의존하고 있음 (OCP 위반)
 */

public class OrderServiceImpl implements OrderService {

	private final MemberRepository memberRepository;
	private final DiscountPolicy discountPolicy;

	public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
		this.memberRepository = memberRepository;
		this.discountPolicy = discountPolicy;
	}

	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		Member member = memberRepository.findById(memberId);
		int discountPrice = discountPolicy.discount(member, itemPrice);
		return new Order(memberId, itemName, itemPrice, discountPrice);
	}

	// test 용도
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}
}
