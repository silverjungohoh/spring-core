package com.practice.springcore.beanfind;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.practice.springcore.AppConfig;
import com.practice.springcore.member.MemberService;
import com.practice.springcore.member.MemberServiceImpl;

public class ApplicationContextBasicFindTest {

	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

	@DisplayName("빈 이름으로 스프링 빈을 조회한다.")
	@Test
	void findBeanByName() {
		MemberService memberService = ac.getBean("memberService", MemberService.class);
		assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
	}

	@DisplayName("이름 없이 타입만으로 스프링 빈을 조회한다.")
	@Test
	void findBeanByType1() {
		MemberService memberService = ac.getBean(MemberService.class);
		assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
	}

	@DisplayName("구현체의 타입만으로 스프링 빈을 조회한다.")
	@Test
	void findBeanByType2() {
		MemberService memberService = ac.getBean(MemberServiceImpl.class);
		assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
	}

	@DisplayName("이름으로 스프링 빈 조회 시 존재하지 않으면 예외가 발생한다.")
	@Test
	void findBeanByNameX() {
		assertThrows(NoSuchBeanDefinitionException.class,
			() -> ac.getBean("xxx", MemberService.class)
		);
	}
}
