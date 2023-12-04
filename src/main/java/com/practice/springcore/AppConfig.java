package com.practice.springcore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

@Configuration
public class AppConfig {

	// @Bean : 스프링 컨테이너에 스프링 빈으로 등록
	// 스프링 컨테이너에 객체를 스프링 빈으로 등록하고, 스프링 컨테이너에서 스프링 빈을 찾아서 사용할 수 있도록 변경됨

	@Bean
	public MemberService memberService() {
		System.out.println("call AppConfig.memberService");
		return new MemberServiceImpl(memberRepository());
	}

	@Bean
	public MemberRepository memberRepository() {
		System.out.println("call AppConfig.memberRepository");
		return new MemoryMemberRepository();
	}

	@Bean
	public OrderService orderService() {
		System.out.println("call AppConfig.orderService");
		return new OrderServiceImpl(memberRepository(), discountPolicy());
	}

	// FixPolicy > RatePolicy 변경해도 구성 영역만 영향을 받고, 사용 영역은 전혀 영향을 받지 않음
	@Bean
	public DiscountPolicy discountPolicy() {
		// return new FixDiscountPolicy();
		return new RateDiscountPolicy();
	}
}

// AppConfig 등장으로 애플리케이션이 크게 사용 영역과, 객체를 생성하고 구성하는 영역으로 분리되었음