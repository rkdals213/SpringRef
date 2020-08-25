package com.example.demo.model.dao;

import com.example.demo.model.dto.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class MemberDao {

    private final EntityManager em;

    public MemberDao(EntityManager em) {
        this.em = em;
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m join fetch m.team t", Member.class).getResultList();
    }



}
