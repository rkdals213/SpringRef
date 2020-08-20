package com.example.demo.model.service;

import com.example.demo.model.dao.dataDao;
import com.example.demo.model.dto.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class dataService {
    @Autowired
    dataDao dao;

    public Data save(Data dto) {
        return dao.save(dto);
    }

    public Optional<Data> findById(int id) {
        return dao.findById(id);
    }

    public Optional<Data> findByData(String data) {
        return dao.findByData(data);
    }

    public List<Data> findAll() {
        return dao.findAll();
    }

    public Data update(Data dto) {
        return dao.update(dto);
    }

    public void deleteById(int id){
        dao.deleteById(id);
    }

    public void deleteByData(String data){
        dao.deleteByData(data);
    }
}
