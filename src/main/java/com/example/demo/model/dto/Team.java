package com.example.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
@NamedEntityGraph(name = "TeamWithMember", attributeNodes = @NamedAttributeNode("members"))
public class Team {
    @Id
    private int id;
    private String name;

    //    @OneToMany(mappedBy = "team")
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    @JsonManagedReference
//    @JsonBackReference
    private Set<Member> members = new HashSet<>();

    public void addMember(Member member){
        member.setTeam(this);
        this.members.add(member);
    }

}
