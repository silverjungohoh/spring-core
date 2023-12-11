package com.practice.springcore.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 스프링 빈의 이벤트 라이프 사이클
// 스프링 컨테이너 생성 > 스프링 빈 생성 > 의존관계 주입 > 초기화 콜백 > 사용 > 소멸 전 콜백 > 스프링 종료
// 초기화 콜백 : 빈이 생성되고, 빈의 의존관계 주입 후 호출됨
// 소멸 전 콞백 : 빈이 소멸되기 직전에 호출됨

public class BeanLifeCycleTest {

	@Test
	void lifeCycleTest() {
		ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
		NetworkClient networkClient = ac.getBean(NetworkClient.class);
		ac.close(); // 스프링 컨테이너를 종료
	}

	@Configuration
	static class LifeCycleConfig {

		// @Bean(initMethod = "init", destroyMethod = "close")
		@Bean
		public NetworkClient networkClient() {
			NetworkClient networkClient = new NetworkClient();
			// 객체 생성 단계에는 url 존재하지 않고,
			// 객체를 생성한 후 외부에서 수정자 주입을 통해 setUrl()이 호출 되어야 url 존재함
			networkClient.setUrl("https://hello-spring.dev");
			return networkClient;
		}
	}
}

// 빈 생명주기 콜백 지원 방법
// 1. 인터페이스 (InitializingBean, DisposableBean >> afterPropertiesSet(), destroy())
// 2. 설정 정보에 초기화 메서드, 종료 메서드 지정
// 3. @PostConstruct, @PreDestroy 어노테이션 지원