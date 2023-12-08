package com.practice.springcore.scope;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

// 스프링 컨테이너는 프로토타입 빈을 생성하고, 의존관계 주입, 초기화까지만 처리
// 클라이언트에 빈을 반환하고, 이후 스프링 컨테이너는 생성된 프로토타입 빈을 관리하지 않음
// 프로토타입 빈을 관리할 책임은 프로토타입 빈을 받은 클라이언트에 있음

public class PrototypeTest {

	@DisplayName("프로토타입 스코프를 스프링 컨테이너에 조회하면 항상 새로운 프로토타입 빈을 생성하여 반환한다.")
	@Test
	void prototypeBeanFind() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
		// 프로토타입 스프링 빈은 스프링 컨테이너에서 빈을 조회할 때 생성되고, 초기화 메서드가 실행됨
		System.out.println("find prototypeBean1");
		PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
		System.out.println("find prototypeBean2");
		PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

		System.out.println("prototypeBean1 = " + prototypeBean1);
		System.out.println("prototypeBean2 = " + prototypeBean2);

		assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

		ac.close();
	}

	@Scope("prototype")
	static class PrototypeBean {

		@PostConstruct
		public void init() {
			System.out.println("PrototypeBean.init");
		}

		@PreDestroy
		public void destroy() { // 종료 메서드가 호출되지 않음
			System.out.println("PrototypeBean.destroy");
		}
	}
}
