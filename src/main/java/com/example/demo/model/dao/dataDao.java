package com.example.demo.model.dao;

import com.example.demo.model.dto.Data;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class dataDao {

    private final EntityManager em;

    public dataDao(EntityManager em) {
        this.em = em;
    }

    public Data save(Data dto) {
        em.persist(dto);
        return dto;
    }

    public Optional<Data> findById(int id) {
        Data dto = em.find(Data.class, id);
        return Optional.ofNullable(dto);
    }

    public Optional<Data> findByData(String data) {
        List<Data> result = em.createQuery("select d from Data d where d.data = :data", Data.class)
                .setParameter("data", data)
                .getResultList();
        return result.stream().findAny();
    }

    public List<Data> findAll() {
        return em.createQuery("select d from Data d", Data.class).getResultList();
    }

    public Data update(Data dto){
        Data data = em.find(Data.class, dto.getId());
        data.setData(dto.getData());
        return dto;
    }

    public void deleteById(int id){
        Data data = em.find(Data.class, id);
        em.remove(data);
    }

    @Transactional @Modifying
    public void deleteByData(String data){
        List<Data> result = em.createQuery("select d from Data d where d.data = :data", Data.class)
                .setParameter("data", data)
                .getResultList();
        for (Data res: result) {
            em.remove(res);
        }
    }
}
