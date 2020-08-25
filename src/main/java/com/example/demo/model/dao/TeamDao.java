package com.example.demo.model.dao;

import com.example.demo.model.dto.Team;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class TeamDao {

    private final EntityManager em;

    public TeamDao(EntityManager em) {
        this.em = em;
    }
    public List<Team> findAll() {
        return em.createQuery("select t from Team t join fetch t.members m", Team.class).getResultList();
    }
}
