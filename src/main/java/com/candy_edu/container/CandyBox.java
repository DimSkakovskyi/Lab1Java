package com.candy_edu.container;

import com.candy_edu.candy.Candy;

import java.util.List;

public class CandyBox {
    private Integer id;       // <-- нове поле для збереження з БД
    private String name;
    private List<Candy> candies;

    public CandyBox(Integer id, String name, List<Candy> candies) {
        this.id = id;
        this.name = name;
        this.candies = candies;
    }

    public CandyBox(String name, List<Candy> candies) {
        this(null, name, candies);
    }

    // --- гетери/сетери ---
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Candy> getCandies() { return candies; }
    public void setCandies(List<Candy> candies) { this.candies = candies; }

    @Override
    public String toString() {
        return "CandyBox{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", candies=" + candies +
                '}';
    }
}
