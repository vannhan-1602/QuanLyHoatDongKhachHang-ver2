package com.DDD.demo.domain.customer;

public class Lead {
    private Long id;
    private String tenLead;

    public Lead(Long id, String tenLead) {
        this.id = id;
        this.tenLead = tenLead;
    }

    public Long getId() { return id; }
    public String getTenLead() { return tenLead; }
}