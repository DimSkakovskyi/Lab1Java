package com.candy_edu.candy_system;

import com.candy_edu.candy.*;

import java.util.List;

public class CandySystem {
    public void getCandies(List<Candy> candies) {
        for(Candy candy : candies)
            System.out.println(candy.toString());
        System.out.println();
    }

    public void getTotalWeightAndPrice(List<Candy> candies){
        Integer totalWeight = 0;
        Double totalPrice = 0.0;
        for(Candy candy : candies){
            totalWeight += candy.getWeight().getGrams();
            totalPrice += candy.getPrice();
        }
        System.out.println("Total weight: " + totalWeight + "g, Total price: " + totalPrice + " UAH\n");
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

    public void findBySugarRange(Integer minSugar, Integer maxSugar, List<Candy> candies){
        System.out.println("Find by Sugar Range method.");
        boolean isEmpty = true;
        for(Candy candy : candies){
            int s = candy.getSugar();
            if(s >= minSugar && s <= maxSugar){
                System.out.println(candy);
                isEmpty = false;
            }
        }
        if(isEmpty)
            System.out.println("No candies found with sugar between "
                    + minSugar + "g and " + maxSugar + "g.");
    }
}

