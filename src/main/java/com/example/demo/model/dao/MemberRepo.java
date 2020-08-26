package com.example.demo.model.dao;

import com.example.demo.model.dto.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepo extends JpaRepository<Member, String> {

    @EntityGraph("MemberWithTeam")
    List<Member> findAll();

    void deleteById(int id);

    Member findById(int member_id);
}
