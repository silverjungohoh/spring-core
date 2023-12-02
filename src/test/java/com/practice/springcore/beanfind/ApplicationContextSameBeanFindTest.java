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

import com.practice.springcore.member.MemberRepository;
import com.practice.springcore.member.MemoryMemberRepository;

public class ApplicationContextSameBeanFindTest {

	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

	@DisplayName("타입으로 조회 시 같은 타입이 둘 이상인 경우 예외가 발생한다.")
	@Test
	void findBeanByTypeDuplicate() {
		assertThrows(NoUniqueBeanDefinitionException.class,
			() -> ac.getBean(MemberRepository.class)
		);
	}

	@DisplayName("타입으로 조회 시 같은 타입이 둘 이상인 경우 빈 이름을 지정한다.")
	@Test
	void findBeanByName() {
		MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);
		assertThat(memberRepository).isInstanceOf(MemberRepository.class);
	}

	@DisplayName("특정 타입의 빈을 모두 조회한다.")
	@Test
	void findAllBeanByType() {
		Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
		for(String key : beansOfType.keySet()) {
			System.out.println("key = " + key + " value = "+ beansOfType.get(key));
			System.out.println("beansOfType = " + beansOfType);
		}
		assertThat(beansOfType.size()).isEqualTo(2);
	}

	@Configuration
	static class SameBeanConfig {

		@Bean
		public MemberRepository memberRepository1() {
			return new MemoryMemberRepository();
		}

		@Bean
		public MemberRepository memberRepository2() {
			return new MemoryMemberRepository();
		}
	}
}
