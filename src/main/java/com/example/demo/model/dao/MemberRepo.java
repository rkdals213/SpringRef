package com.example.demo.model.dao;

import com.example.demo.model.dto.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepo extends JpaRepository<Member, String> {

    @EntityGraph("MemberWithTeam")
    List<Member> findAll();
}
