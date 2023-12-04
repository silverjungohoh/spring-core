package com.practice.springcore.singleton;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.practice.springcore.AppConfig;
import com.practice.springcore.member.MemberService;

// 스프링 없는 순수한 DI 컨테이너인 AppConfig >> 요청할 때마다 객체를 새로 생성함 (메모리 낭비가 심함)
// 해당 객체가 1개만 생성되고, 공유하도록 설계하면 된다 (싱글톤 패턴)

public class SingletonTest {

	@DisplayName("스프링 없는 순수한 DI 컨테이너를 생성한다.")
	@Test
	void pureContainer() {
		AppConfig appConfig = new AppConfig();

		// 1. 호출할 때마다 객체를 생성
		MemberService memberService1 = appConfig.memberService();
		// 2. 호출할 때마다 객체를 생성
		MemberService memberService2 = appConfig.memberService();

		// 참조값이 다른 것을 확인
		System.out.println("memberService1 = " + memberService1);
		System.out.println("memberService2 = " + memberService2);

		assertThat(memberService1).isNotSameAs(memberService2);
	}

	@DisplayName("싱글톤 패턴을 적용한 객체를 사용한다.")
	@Test
	void singletonServiceTest() {
		SingletonService singletonService1 = SingletonService.getInstance();
		SingletonService singletonService2 = SingletonService.getInstance();

		// 싱글톤 패턴 적용 시 고객의 요청이 올 때마다 객체를 생성하는 것이 아니라,
		// 이미 만들어진 객체를 공유하여 효율적으로 사용할 수 있음
		System.out.println("singletonService1 = " + singletonService1);
		System.out.println("singletonService2 = " + singletonService2);

		// 호출할 때마다 같은 객체 인스턴스를 반환함
		assertThat(singletonService1).isSameAs(singletonService2);
	}

	@DisplayName("스프링 컨테이너는 객체 인스턴스를 싱글톤으로 관리한다.")
	@Test
	void springContainer() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		// 호출할 때마다 동일한 객체를 반환
		MemberService memberService1 = ac.getBean("memberService", MemberService.class);
		MemberService memberService2 = ac.getBean("memberService", MemberService.class);

		// 참조값이 같음
		System.out.println("memberService1 = " + memberService1);
		System.out.println("memberService2 = " + memberService2);

		assertThat(memberService1).isSameAs(memberService2);
	}
}
