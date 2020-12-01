package com.inflearn.hellospringboot.service;

import com.inflearn.hellospringboot.domain.Member;
import com.inflearn.hellospringboot.repository.MemberRepository;
import com.inflearn.hellospringboot.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemoryMemberRepository repository) {
        this.memberRepository = repository;
    }

    /**
     *  회원가입
     * @param member
     * @return
     */
    public Long join(Member member){

        // 이름 중복 블락
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    protected void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(members -> {
                throw new IllegalStateException("Already user name exists.");
            });
//        Optional<Member> result = memoryMemberRepository.findByName(member.getName());
//        result.ifPresent(members -> {
//            throw new IllegalStateException("Already user name exists.");
//        });
    }

    /**
     *  전체 회원 조회
     * @return
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     *  개별 회원 조회
     * @param memberId
     * @return
     */
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
