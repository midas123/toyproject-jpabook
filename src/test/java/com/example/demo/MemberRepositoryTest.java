package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Member;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {
	
	@Autowired MemberRepository memberRepository;
	
	@Test
	@Transactional //테스트에서 트랜잭션은 테스트 종료 후 롤백
	@Rollback(false)
	public void testMember() throws Exception{
		//given
		Member member = new Member();
		member.setName("aaa");
		
		//when
		Long savedId = memberRepository.save(member);
		Member findMember = memberRepository.find(savedId);
		
		//then
		Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
		Assertions.assertThat(findMember.getName()).isEqualTo(member.getName());
		Assertions.assertThat(findMember).isEqualTo(member);
	}
}
