package com.lunch.recipes.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Ingredient implements Comparable {

    private String title;

    @JsonProperty("best-before")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date bestBefore;

    @JsonProperty("use-by")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date useBy;

    @Override
    public int compareTo(Object o) {
        return bestBefore.compareTo(((Ingredient) o).getBestBefore());
    }

    public Ingredient(String title) {
        this.title = title;
    }
}
