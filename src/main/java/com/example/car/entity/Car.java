package com.example.car.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String model;

    private Boolean isReserved;

    private Integer customerId;

    private Date startTime;

    private Date endTime;

}
