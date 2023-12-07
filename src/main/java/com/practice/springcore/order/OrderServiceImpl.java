package com.practice.springcore.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.practice.springcore.annotation.MainDiscountPolicy;
import com.practice.springcore.discount.DiscountPolicy;
import com.practice.springcore.member.Member;
import com.practice.springcore.member.MemberRepository;

/**
 * DiscountPolicy 인터페이스에 의존, but 구현 클래스(FixDiscountPolicy, RateDiscountPolicy)에 의존하고 있음 (OCP 위반)
 */

@Component
// @RequiredArgsConstructor : final 붙은 필드를 모아서 생성자를 자동으로 만들어줌
public class OrderServiceImpl implements OrderService {

	private final MemberRepository memberRepository;
	private final DiscountPolicy discountPolicy;

	@Autowired
	public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
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
