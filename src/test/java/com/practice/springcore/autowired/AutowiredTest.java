package com.practice.springcore.autowired;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.practice.springcore.member.Member;

import jakarta.annotation.Nullable;

// 주입할 스프링 빈이 없어도 동작해야 하는 경우가 존재
// @Autowired 사용하면 required 옵션이 기본값 true 되어 있어 자동 주입 대상이 없으면 오류가 발생
// 자동 주입 대상을 옵션으로 처리하는 방법은 총 3가지

public class AutowiredTest {

	@Test
	void autowiredOption() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
	}

	static class TestBean { // Member 스프링 빈이 아님

		// 자동 주입할 대상이 없으면 메서드 자체가 호출 안됨
		@Autowired(required = false)
		public void setNoBean1(Member member) {
			System.out.println("setNoBean1 = " + member);
		}

		// 자동 주입할 대상이 없으면 null 호출
		@Autowired
		public void setNoBean2(@Nullable Member member) {
			System.out.println("setNoBean2 = " + member);
		}

		// 자동 주입할 대상이 없으면 Optional.empty 호출
		@Autowired(required = false)
		public void setNoBean3(Optional<Member> member) {
			System.out.println("setNoBean3 = " + member);
		}
	}
}
