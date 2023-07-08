package com.example.patients.entity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;


@Data
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_by")
    public String CreatedBy;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    public Date CreatedAt;

    @Column(name = "updated_by")
    public String UpdatedBy;

    @UpdateTimestamp
    @Column(name = "updated_at")
    public Date UpdatedAt;
}
