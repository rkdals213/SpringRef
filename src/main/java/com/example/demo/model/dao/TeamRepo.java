package com.example.demo.model.dao;

import com.example.demo.model.dto.Member;
import com.example.demo.model.dto.Team;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepo extends JpaRepository<Team, String> {

    @EntityGraph("TeamWithMember")
    List<Team> findAll();

    Team findById(int team_id);
    Team findByName(String name);
}
