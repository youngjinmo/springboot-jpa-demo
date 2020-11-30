package com.inflearn.hellospringboot.service;

import com.inflearn.hellospringboot.domain.Member;
import com.inflearn.hellospringboot.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();

    /**
     *  회원가입
     * @param member
     * @return
     */
    public Long join(Member member){

        // 이름 중복 블락
        validateDuplicateMember(member);

        memoryMemberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memoryMemberRepository.findByName(member.getName())
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
        return memoryMemberRepository.findAll();
    }

    /**
     *  개별 회원 조회
     * @param memberId
     * @return
     */
    public Optional<Member> findOne(Long memberId){
        return memoryMemberRepository.findById(memberId);
    }

}
