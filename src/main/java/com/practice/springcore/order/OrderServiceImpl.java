package com.practice.springcore.order;

import com.practice.springcore.discount.DiscountPolicy;
import com.practice.springcore.discount.FixDiscountPolicy;
import com.practice.springcore.member.Member;
import com.practice.springcore.member.MemberRepository;
import com.practice.springcore.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

	private final MemberRepository memberRepository = new MemoryMemberRepository();
	private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		Member member = memberRepository.findById(memberId);
		int discountPrice = discountPolicy.discount(member, itemPrice);
		return new Order(memberId, itemName, itemPrice, discountPrice);
	}
}
