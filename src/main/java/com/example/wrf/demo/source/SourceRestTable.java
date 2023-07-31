package com.example.wrf.demo.source;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Table(name = "source_rest")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SourceRestTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
