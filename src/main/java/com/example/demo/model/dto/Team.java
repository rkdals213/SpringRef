package com.example.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
@NamedEntityGraph(name = "TeamWithMember", attributeNodes = @NamedAttributeNode("members"))
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(mappedBy = "team")
    @JsonManagedReference
//    @JsonBackReference
    private Set<Member> members = new HashSet<>();

    public void addMember(Member member){
        member.setTeam(this);
        this.members.add(member);
    }

}
