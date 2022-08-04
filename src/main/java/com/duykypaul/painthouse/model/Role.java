package com.duykypaul.painthouse.model;

import com.duykypaul.painthouse.common.Constant.AUTH.ROLE;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ROLE name;

    public Role() {
    }

    public Role(ROLE name) {
        this.name = name;
    }
}
