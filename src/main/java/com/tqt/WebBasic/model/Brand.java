package com.tqt.WebBasic.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Generated
@NoArgsConstructor
@ToString
@Entity
@Table(name = "brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nameBrand;
}
