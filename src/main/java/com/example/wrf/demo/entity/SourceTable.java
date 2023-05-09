package com.example.wrf.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Entity
@Table(name = "source_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SourceTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer age;

    private String name;

    private String key;

    @ManyToOne(cascade = CascadeType.MERGE)
    private SourceRestTable restTable;
}
