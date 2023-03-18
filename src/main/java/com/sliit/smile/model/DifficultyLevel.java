package com.sliit.smile.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class DifficultyLevel {
    @Id
    private String name;
    private int point;
}
