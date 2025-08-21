package com.neroforte.theraven_testtask.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer_table")

public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long created;

    @Column(nullable = false)
    private Long updated;

    @Column(name = "full_name", length = 255, nullable = false)
    private String fullName;

    @Column(unique = true, length = 255, nullable = false)
    private String email;

    @Column(name = "phone", length = 255)
    private String phone;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @PrePersist
    private void autoSetCreatedDate(){
        long timestamp = System.currentTimeMillis();        // not quite sure on how to save date to bigint
        this.created = timestamp;                           // will use millisec to store in unix timestamp format
        this.updated = timestamp;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updated = System.currentTimeMillis();
    }


}
