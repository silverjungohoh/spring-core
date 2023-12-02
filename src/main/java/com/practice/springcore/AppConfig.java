package com.practice.springcore;

import com.practice.springcore.discount.DiscountPolicy;
import com.practice.springcore.discount.RateDiscountPolicy;
import com.practice.springcore.member.MemberRepository;
import com.practice.springcore.member.MemberService;
import com.practice.springcore.member.MemberServiceImpl;
import com.practice.springcore.member.MemoryMemberRepository;
import com.practice.springcore.order.OrderService;
import com.practice.springcore.order.OrderServiceImpl;

// 실제 동작에 필요한 구현 객체를 생성한다
// 생성한 객체 인스턴스의 참조를 '생성자를 통해서 주입' 해준다

public class AppConfig {

	public MemberService memberService() {
		return new MemberServiceImpl(memberRepository());
	}

	public MemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}

	public OrderService orderService() {
		return new OrderServiceImpl(memberRepository(), discountPolicy());
	}

	// FixPolicy > RatePolicy 변경해도 구성 영역만 영향을 받고, 사용 영역은 전혀 영향을 받지 않음
	public DiscountPolicy discountPolicy() {
		// return new FixDiscountPolicy();
		return new RateDiscountPolicy();
	}
}

// AppConfig 등장으로 애플리케이션이 크게 사용 영역과, 객체를 생성하고 구성하는 영역으로 분리되었음