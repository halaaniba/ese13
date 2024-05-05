package com.example.ese13.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Month {

    private int monthNumber;
    private String englishName;
    private String italianName;
    private String spanishName;
}

