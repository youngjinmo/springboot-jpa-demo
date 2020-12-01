package com.inflearn.hellospringboot.repository;

import com.inflearn.hellospringboot.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()  // lambda식으로 loop
                .filter(member -> member.getName().equals(name)) // name과 일치하는 Member 객체만 필터링
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        ArrayList list = new ArrayList<>(store.values());
        return new ArrayList<>(store.values());  // map을 list로 변환해서 반환
    }

    public void clearStore(){
        store.clear();   // test 메서드 실행 후 캐시 삭제하기 위함.
    }
}
