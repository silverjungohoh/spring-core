package com.practice.springcore.scan.filter;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

public class ComponentFilterAppConfigTest {

	@DisplayName("컴포넌트 스캔 대상에 추가 혹은 제외된 클래스를 확인한다.")
	@Test
	void filterScan() {

		ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
		BeanA beanA = ac.getBean("beanA", BeanA.class);

		assertThat(beanA).isNotNull();
		assertThrows(NoSuchBeanDefinitionException.class,
			() -> ac.getBean("beanB", BeanB.class)
		);
	}

	@Configuration
	@ComponentScan(
		includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
		excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
	)
	static class ComponentFilterAppConfig {

	}
	// includeFilters : 컴포넌트 스캔 대상을 추가로 지정
	// excludeFilters : 컴포넌트 스캔에서 제외할 대상을 지정
}
