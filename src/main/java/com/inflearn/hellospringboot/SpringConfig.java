package com.inflearn.hellospringboot;

import com.inflearn.hellospringboot.repository.JpaMemberRepository;
import com.inflearn.hellospringboot.repository.MemberRepository;
import com.inflearn.hellospringboot.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class SpringConfig {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em){
        this.em = em;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
     // return new MemoryMemberRepository();
     // return new JdbcMemberRepository(datasource);
     // return new JdbcTemplateMemberRepository(datasource);
        return new JpaMemberRepository(em);
    }
}
