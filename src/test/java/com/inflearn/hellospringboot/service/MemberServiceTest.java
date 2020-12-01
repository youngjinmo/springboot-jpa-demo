package com.inflearn.hellospringboot.service;

import com.inflearn.hellospringboot.domain.Member;
import com.inflearn.hellospringboot.repository.MemoryMemberRepository;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository repository;

    @BeforeEach
    public void beforeEach(){
        repository = new MemoryMemberRepository();
        memberService = new MemberService(repository);
    }


    @AfterEach
    void clearStore(){
        repository.clearStore();
    }

    @Test
    @Order(1)
    @DisplayName("회원가입 테스트")
    void join() {
        // given
        Member member = new Member();
        member.setName("spring1");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    @Order(2)
    @DisplayName("회원 중복검사 테스트")
    void validDuplicationMember(){
        // given
        Member member1 = new Member();
        member1.setName("spring1");

        Member member2 = new Member();
        member2.setName("spring2");

        // when
        memberService.join(member1);

        // then
        memberService.validateDuplicateMember(member2);

    }

    @Test
    @Order(3)
    @DisplayName("전체 회원 조회 테스트")
    void findMembers() {
        // given
        Member member1 = new Member();
        member1.setName("spring1");

        Member member2 = new Member();
        member2.setName("spring2");

        // when
        memberService.join(member1);
        memberService.join(member2);

        // then
        assertEquals(memberService.findMembers().size(),2);

    }

    @Test
    @Order(4)
    @DisplayName("개별 회원 조회 테스트")
    void findOne() {
        // given
        Member member = new Member();
        member.setName("spring-boot");

        Member member2 = new Member();
        member2.setName("spring-framework");

        // when
        memberService.join(member);
        Long userId = member.getId();

        // then
        assertEquals(memberService.findOne(userId).get(),member);

    }
}