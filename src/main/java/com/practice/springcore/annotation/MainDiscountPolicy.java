package com.practice.springcore.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.beans.factory.annotation.Qualifier;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {
}

// @Autowired 필드 이름 매칭 : 여러 빈이 있으면 필드 이름, 파라미터 이름으로 빈 이름을 추가 매칭
// @Qualifier : 추가 구분자를 붙여주는 방법, 주입 시에 @Qualifier 붙이고 등록한 이름을 적어줌
// @Primary : 우선 순위를 정하는 방법, 여러 빈이 매칭되면 @Primary 우선권을 가짐