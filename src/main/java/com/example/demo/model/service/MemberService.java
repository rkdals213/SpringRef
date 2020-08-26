package com.example.demo.model.service;

import com.example.demo.model.dao.MemberDao;
import com.example.demo.model.dto.Member;
import com.example.demo.model.dao.MemberRepo;
import com.example.demo.model.dto.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MemberService {
    @Autowired
    MemberDao dao;

    @Autowired
    MemberRepo repo;

    public List<Member> findAll() {
        return repo.findAll();
//        return dao.findAll();
    }

    public Member addMember(Member member){
        return repo.save(member);
    }

    public void deleteById(int id){
        repo.deleteById(id);
    }

    public void changeTeam(int member_id ,int to_team_id){
        Member mem = repo.findById(member_id);
        Team team = new Team();
        team.setId(to_team_id);
        mem.setTeam(team);
    }
}
