package com.practice.springcore.scope;

// 싱글톤 빈과 프로토타입 빈을 함께 사용할 때 항상 새로운 프로토타입 빈을 생성 >> Provider 사용

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class PrototypeProviderTest {

	@DisplayName("싱글톤 빈이 프로토타입을 사용할 때마다 스프링 컨테이너에 새로 요청한다.")
	@Test
	void providerTest() {
		AnnotationConfigApplicationContext ac
			= new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

		ClientBean clientBean1 = ac.getBean(ClientBean.class);
		int count1 = clientBean1.logic();
		assertThat(count1).isEqualTo(1);

		ClientBean clientBean2 = ac.getBean(ClientBean.class);
		int count2 = clientBean2.logic();
		assertThat(count2).isEqualTo(1);
	}

	static class ClientBean {

		// 직접 필요한 의존관계를 찾는 것 (의존관계 조회) : DL >> Dependency Lookup
		// 스프링 애플리케이션 컨텍스트 전체를 주입받는 경우, 스프링 컨테이너에 종속적인 코드가 됨
		// @Autowired
		// private ApplicationContext ac;

		@Autowired
		private ObjectProvider<PrototypeBean> prototypeBeanProvider;

		public int logic() {
			// PrototypeBean prototypeBean = ac.getBean(PrototypeBean.class);
			PrototypeBean prototypeBean = prototypeBeanProvider.getObject(); // 지정한 빈을 컨테이너에서 대신 찾아줌
			prototypeBean.addCount();
			return prototypeBean.getCount();
		}
	}

	@Scope("prototype")
	static class PrototypeBean {
		private int count = 0;

		public void addCount() {
			count++;
		}

		public int getCount() {
			return count;
		}

		@PostConstruct
		public void init() {
			System.out.println("PrototypeBean.init");
		}

		@PreDestroy
		public void destroy() {
			System.out.println("PrototypeBean.destroy");
		}
	}
}
