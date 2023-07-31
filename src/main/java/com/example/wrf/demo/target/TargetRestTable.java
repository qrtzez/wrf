package com.example.wrf.demo.target;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Table(name = "target_rest")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TargetRestTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
