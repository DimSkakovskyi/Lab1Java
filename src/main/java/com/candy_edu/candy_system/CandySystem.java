package com.candy_edu.candy_system;

import com.candy_edu.candy.Candy;
import com.candy_edu.candy.CandyType;
import java.util.ArrayList;
import java.util.List;

public class CandySystem {
    public void getCandies(List<Candy> candies) {
        for(Candy candy : candies)
            System.out.println(candy.toString());
        System.out.println();
    }

    public int getTotalWeight(List<Candy> candies) {
        int totalWeight = 0;
        for (Candy candy : candies) {
            totalWeight += candy.getWeight().getGrams();
        }
        return totalWeight;
    }

    public double getTotalPrice(List<Candy> candies) {
        double totalPrice = 0.0;
        for (Candy candy : candies) {
            totalPrice += candy.getPrice();
        }
        return totalPrice;
    }


    public void sortingByType(List<Candy> candies, CandyType type){
        System.out.println("\nSorting by Type method.");
        for(Candy candy : candies) {
            if (candy.getType() == type)
                System.out.println(candy);
        }
        for(Candy candy : candies) {
            if(candy.getType() != type)
                System.out.println(candy);
        }
        System.out.println();
    }

    public List<Candy> findBySugarRange(List<Candy> candies, int minSugar, int maxSugar) {
        List<Candy> result = new ArrayList<>();
        for (Candy candy : candies) {
            if (candy.getSugar() >= minSugar && candy.getSugar() <= maxSugar) {
                result.add(candy);
            }
        }
        return result;
    }
}

