package com.example.demo.model.service;

import com.example.demo.model.dao.TeamDao;
import com.example.demo.model.dto.Data;
import com.example.demo.model.dto.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TeamService {
    @Autowired
    TeamDao dao;

    public List<Team> findAll() {
        return dao.findAll();
    }
}
