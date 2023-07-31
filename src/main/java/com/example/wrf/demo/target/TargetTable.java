package com.example.wrf.demo.target;

import jakarta.persistence.*;
import lombok.*;

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

    @Column()
    private String keyP;

    @Column
    private Integer money;
}
