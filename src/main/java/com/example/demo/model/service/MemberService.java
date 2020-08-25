package com.example.demo.model.service;

import com.example.demo.model.dao.MemberDao;
import com.example.demo.model.dto.Member;
import com.example.demo.model.dao.MemberRepo;
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
//        return repo.findAll();
        return dao.findAll();
    }
}
