package com.practice.springcore.singleton;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

	// 스프링 빈의 필드에 공유 값을 설정하면 안됨
	@DisplayName("싱글톤 객체는 무상태로 설계해야 한다.")
	@Test
	void statefulServiceSingleton() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

		StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
		StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

		// ThreadA : A 사용지 10000원 주문
		statefulService1.order("userA", 10000);
		// ThreadB : B 사용자 20000원 주문
		statefulService2.order("userB", 20000);

		// A 사용자 주문 금액 조회
		int priceA = statefulService1.getPrice();
		// A 사용자는 10000원을 기대했으나, 기대와 다르게 20000원 출력
		System.out.println("priceA = " + priceA);

		assertThat(statefulService1.getPrice()).isEqualTo(20000);
	}

	static class TestConfig {

		@Bean
		public StatefulService statefulService() {
			return new StatefulService();
		}
	}
}