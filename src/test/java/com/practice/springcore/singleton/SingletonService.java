package com.practice.springcore.singleton;


public class SingletonService {

	// static 영역에 객체를 1개만 생성해둠
	private static final SingletonService instance = new SingletonService();

	// public 사용하여 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용함
	public static SingletonService getInstance() {
		return instance;
	}

	// 생성자를 private 선언하여 외부에서 new 사용한 객체 생성을 못하도록 막음
	private SingletonService() {
	}
}
