package com.example.demo.manytomany;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class MemberProduct {
    @Id
    @GeneratedValue
    @Column(name="member_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int counts;

    private LocalDateTime orderDateTime;
}
