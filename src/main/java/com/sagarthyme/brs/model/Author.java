package com.sagarthyme.brs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "UNIQUE_Author_Author_Name", columnNames = "name"))
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(length = 50)
    public String name;

    @Column(length = 80, unique = true)
    public String email;

    @Column(length = 10, unique = true)
    public String mobileNumber;
}
