package com.example.demo.model.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Data {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String data;
}
