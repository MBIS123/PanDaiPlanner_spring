package com.FYP.PandaiPlanner.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "\"financialProfile\"")
public class FinancialProfile {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long financialProfileId;




}
