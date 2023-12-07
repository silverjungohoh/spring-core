package com.practice.springcore.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;

	@Autowired // 의존 관계를 자동으로 주입
	public MemberServiceImpl(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Override
	public void join(Member member) {
		memberRepository.save(member);
	}

	@Override
	public Member findMember(Long memberId) {
		return memberRepository.findById(memberId);
	}

	// test 용도
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}
}

