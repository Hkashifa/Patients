package com.example.patients.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Data
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_by")
    public String CreatedBy;

    @Column(name = "created_at")
    public Date CreatedAt;

    @Column(name = "updated_by")
    public String UpdatedBy;

    @Column(name = "updated_at")
    public Date UpdatedAt;
}
