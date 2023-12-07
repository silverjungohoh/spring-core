package com.practice.springcore.autowired;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.practice.springcore.AutoAppConfig;
import com.practice.springcore.discount.DiscountPolicy;
import com.practice.springcore.member.Grade;
import com.practice.springcore.member.Member;

public class AllBeanTest {

	@DisplayName("특정 타입의 빈이 모두 필요할 때 List, Map 통해 주입받을 수 있다.")
	@Test
	void findAllBean() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
		DiscountService discountService = ac.getBean(DiscountService.class);

		Member member = new Member(1L, "kevin", Grade.VIP);
		int discountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");

		assertThat(discountService).isInstanceOf(DiscountService.class);
		assertThat(discountPrice).isEqualTo(2000);
	}

	static class DiscountService {
		private final Map<String, DiscountPolicy> policyMap;
		private final List<DiscountPolicy> policies;

		public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
			this.policyMap = policyMap;
			this.policies = policies;
			System.out.println("policyMap = " + policyMap);
			System.out.println("policies = " + policies);
		}

		public int discount(Member member, int price, String discountCode) {
			DiscountPolicy discountPolicy = policyMap.get(discountCode);
			System.out.println("discountCode = " + discountCode);
			System.out.println("discountPolicy = " + discountPolicy);
			return discountPolicy.discount(member, price);
		}
	}
}
