package com.practice.springcore;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
	excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}

// @Component 붙은 모든 클래스를 스프링 빈으로 등록함
// 스프링 빈 기본 이름은 클래스명을 사용하되 맨 앞글자만 소문자를 사용
// 빈 이름을 직접 지정하고 싶다면 @Component("memberService1")와 같이 이름을 부여하면 됨

// basePackages : 탐색할 패키지의 시작 위치를 지정
// 지정하지 않으면 @ComponentScan 붙은 설정 정보 클래스의 패키지가 시작 위치가 됨