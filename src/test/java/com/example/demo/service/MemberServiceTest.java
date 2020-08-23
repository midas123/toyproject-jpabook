package com.example.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
@Transactional
public class MemberServiceTest {

	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;
	@Autowired EntityManager em;
	
	@Test
	@Rollback(false)//테스트에서 트랜잭션 롤백 방지하고 DB에 데이터가 저장됨
	public void 회원가입() {
		//given
		Member member = new Member();
		member.setName("hong");
		
		//when
		Long savedId = memberService.join(member);
		
		//then
		//em.flush(); //insert는 되지만 테스트 종료 후 롤백
		assertEquals(member, memberRepository.findOne(savedId));
	}
	
	@Test(expected = IllegalStateException.class)
	public void 중복_회원_예외() throws Exception {
		//given
		Member member1 = new Member();
		member1.setName("kim");
		
		Member member2 = new Member();
		member2.setName("kim");
		
		//when
		memberService.join(member1);
		memberService.join(member2);
		
		/*
		 * memberService.join(member1); try { memberService.join(member2); }catch
		 * (IllegalStateException e) { return; }
		 */
		
		//then
		fail("테스트 실패: 예외가 발생해야 한다.");
		
	}
}
