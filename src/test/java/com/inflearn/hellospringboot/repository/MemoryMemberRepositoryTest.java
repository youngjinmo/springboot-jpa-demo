package com.inflearn.hellospringboot.repository;

import com.inflearn.hellospringboot.domain.Member;
import com.inflearn.hellospringboot.service.MemberService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @Autowired
    MemberService memberService;

    @AfterEach
    public void afterEach(){
        // test 메서드 실행 후 캐시 삭제하기 위함.
        repository.clearStore();
    }

    @Test
    @Order(1)
    public void findById(){
        // given
        Member member = new Member();
        member.setName("devandy");
        repository.save(member);

        // when
        Member result = repository.findById(member.getId()).get();

        // then
        assertEquals(member, result);   // 기대하는 값 : member 실제 결과 : result 비교하여 boolean 결과 반환

    }

    @Test
    @Order(2)
    public void findByName(){
        // given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // when
        Member result = repository.findByName("spring1").get();

        // then
        assertEquals(member1, result);

    }

    @Test
    @Order(3)
    public void findAll(){
        // given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // when
        List<Member> result = repository.findAll();

        // then
        assertEquals(2, result.size());
    }

}
