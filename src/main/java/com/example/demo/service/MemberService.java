package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import com.sun.xml.txw2.IllegalSignatureException;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor //final 필드 값에 대한 생성자 자동 작성
public class MemberService {
	
	/*
	 * @Autowired 
	 * private MemberRepository memberRepository; //필드 인젝션
	 */
	
	//생성자 인젝션, Autowired 어노테이션 생략 가능
	private final MemberRepository memberRepository;
	/*
	 * @Autowired public MemberService(MemberRepository memberRepository) {
	 * this.memberRepository = memberRepository; }
	 */
	
	/*
	 * @Autowired //setter 인젝션 
	 * public void setMemberRepository(MemberRepository
	 * memberRepository) { this.memberRepository = memberRepository; }
	 * 
	 */
	
	public Long join(Member member) {
		validateDuplicateMember(member);
		memberRepository.save(member);
		return member.getId();
	}
	
	private void validateDuplicateMember(Member member) {
		List<Member> members = memberRepository.findByName(member.getName());
		if(!members.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 회원");
			/*
			 * 동시에 동일한 회원이름으로 회원 가입 신청되는 상황을 대비해서, 
			 * Member테이블의 username에 unique 제약조건을 건다.
			 */
		}
	}
	
	@Transactional(readOnly=true) //읽기 전용 트랜잭션, 성능 최적화
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}
	
	@Transactional(readOnly=true)
	public Member findOne(Long memberId) {
		return memberRepository.findOne(memberId);
	}
}
