package com.jpa.poc.models;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Setter(AccessLevel.NONE)
    private String name;

    @Setter(AccessLevel.NONE)
    private String address;

    @Setter(AccessLevel.NONE)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dept_id", referencedColumnName = "deptId")
    private Department department;

}
