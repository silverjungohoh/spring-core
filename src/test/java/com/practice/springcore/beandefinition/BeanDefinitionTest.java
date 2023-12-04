package com.practice.springcore.beandefinition;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.practice.springcore.AppConfig;

public class BeanDefinitionTest {

	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);


	@DisplayName("스프링 빈 설정 메타 정보를 확인한다.")
	@Test
	void findApplicationBean() {
		String[] beanDefinitionNames = ac.getBeanDefinitionNames();
		for(String name : beanDefinitionNames) {
			BeanDefinition beanDefinition = ac.getBeanDefinition(name);
			if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
				System.out.println("beanDefinitionName = " + name + " beanDefinition = " + beanDefinition);
			}
		}
	}
}
