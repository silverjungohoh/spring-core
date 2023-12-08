package com.practice.springcore.scope;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class SingletonTest {

	@DisplayName("싱글톤 스코프 빈을 조회하면 항상 같은 인스턴스의 스프링 빈을 반환한다.")
	@Test
	void singletonBeanFind() {
		// 빈 초기화 메서드 실행
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);
		// 같은 인스턴스의 빈을 조회
		SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
		SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);

		System.out.println("singletonBean1 = " + singletonBean1);
		System.out.println("singletonBean2 = " + singletonBean2);

		assertThat(singletonBean1).isSameAs(singletonBean2);

		// 종료 메서드 호출
		ac.close();
	}

	@Scope("singleton")
	static class SingletonBean {

		@PostConstruct
		public void init() {
			System.out.println("SingletonBean.init");
		}

		@PreDestroy
		public void destroy() {
			System.out.println("SingletonBean.destroy");
		}
	}
}
