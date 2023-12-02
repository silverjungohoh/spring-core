package com.practice.springcore.beanfind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.practice.springcore.AppConfig;

public class ApplicationContextInfoTest {

	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

	// getBeanDefinitionNames() : 스프링에 등록된 모든 빈 이름을 조회
	// getBean() : 빈 이름으로 빈 객체 (인스턴스)를 조회

	@DisplayName("컨테이너에 등록된 모든 빈을 출력한다.")
	@Test
	void findAllBean() {
		String[] beanDefinitionNames = ac.getBeanDefinitionNames();
		for (String name : beanDefinitionNames) {
			Object bean = ac.getBean(name);
			System.out.println("name = " + name + " object = " + bean);
		}
	}

	@DisplayName("컨테이너에 등록된 모든 애플리케이션 빈을 출력한다.")
	@Test
	void findApplicationBean() {
		String[] beanDefinitionNames = ac.getBeanDefinitionNames();
		for (String name : beanDefinitionNames) {
			BeanDefinition beanDefinition = ac.getBeanDefinition(name);
			// ROLE_APPLICATION : 직접 등록한 애플리케이션 빈, ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
			if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
				Object bean = ac.getBean(name);
				System.out.println("name = " + name + " object = " + bean);
			}
		}
	}
}
