package com.example.wrf.demo.source;

import com.example.wrf.demo.source.SourceRestTable;
import jakarta.persistence.*;
import lombok.*;

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

    private Integer money;

    @ManyToOne(cascade = CascadeType.MERGE)
    private SourceRestTable restTable;
}
