package com.practice.springcore.beanfind;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.practice.springcore.discount.DiscountPolicy;
import com.practice.springcore.discount.FixDiscountPolicy;
import com.practice.springcore.discount.RateDiscountPolicy;

public class ApplicationContextExtendsFindTest {

	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

	@DisplayName("부모 타입으로 조회 시 자식이 둘 이상 있으면 예외가 발생한다.")
	@Test
	void findBeanByParentTypeDuplicate() {
		assertThrows(NoUniqueBeanDefinitionException.class,
			() -> ac.getBean(DiscountPolicy.class)
		);
	}

	@DisplayName("부모 타입으로 조회 시 자식이 둘 이상 있으면 빈 이름을 지정한다.")
	@Test
	void findBeanByParentTypeBeanName() {
		DiscountPolicy discountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
		assertThat(discountPolicy).isInstanceOf(DiscountPolicy.class);
	}

	@DisplayName("특정 하위 타입으로 빈을 조회한다.")
	@Test
	void findBeanBySubType() {
		RateDiscountPolicy rateDiscountPolicy = ac.getBean(RateDiscountPolicy.class);
		assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
	}

	@DisplayName("부모 타입으로 모든 빈을 조회한다.")
	@Test
	void findAllBeanByParentType() {
		Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
		assertThat(beansOfType.size()).isEqualTo(2);
		for(String key : beansOfType.keySet()) {
			System.out.println("key = " + key + " value = "+ beansOfType.get(key));
		}
	}

	@DisplayName("Object 타입으로 모든 빈을 출력한다.")
	@Test
	void findAllBeanByObjectType() {
		Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
		for(String key : beansOfType.keySet()) {
			System.out.println("key = " + key + " value = " + beansOfType.get(key));
		}
	}

	@Configuration
	static class TestConfig {

		@Bean
		public DiscountPolicy rateDiscountPolicy() {
			return new RateDiscountPolicy();
		}

		@Bean
		public DiscountPolicy fixDiscountPolicy() {
			return new FixDiscountPolicy();
		}
	}
}
