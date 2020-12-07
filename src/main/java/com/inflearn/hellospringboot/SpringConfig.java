package com.inflearn.hellospringboot;

import com.inflearn.hellospringboot.repository.MemberRepository;
import com.inflearn.hellospringboot.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

    // @Bean
    // public MemberRepository memberRepository(){
     //  return new MemoryMemberRepository();
     //  return new JdbcMemberRepository(datasource);
     //  return new JdbcTemplateMemberRepository(datasource);
     //  return new JpaMemberRepository(em);
    // }
}
