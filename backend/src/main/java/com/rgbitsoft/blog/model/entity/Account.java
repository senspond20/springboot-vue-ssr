package com.rgbitsoft.blog.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Account {

    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY) : Mysql
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AccountSeqGenerator")
    @SequenceGenerator(sequenceName = "AccountSeq", name = "AccountSeqGenerator", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 30)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 255)
    private String email;

    @CreationTimestamp
    private Timestamp createDate;

    @Column(nullable = false, length = 10)
    private String role;

}


