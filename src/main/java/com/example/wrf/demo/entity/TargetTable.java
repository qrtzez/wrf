package com.example.wrf.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Entity
@Table(name = "target_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TargetTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "old_year")
    private Integer yearOld;

    @Column(name = "first_name")
    private String firstName;

    private String key;

    @ManyToOne(cascade = CascadeType.MERGE)
    private TargetRestTable restTable;
}
